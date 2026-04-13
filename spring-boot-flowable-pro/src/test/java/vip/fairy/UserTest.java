package vip.fairy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.*;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.ui.idm.constant.GroupTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.fairy.flowable.FlowableApplication;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(classes = {FlowableApplication.class})
public class UserTest {

  @Autowired
  IdentityService identityService;

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Autowired
  private RepositoryService repositoryService;

  @Test
  void addUser1() {
    User user = identityService.newUser("user1");
    user.setFirstName("user");
    user.setLastName("one");
    user.setEmail("user1@example.com");
    identityService.saveUser(user);

    Group adminGroup = identityService.newGroup("security-role");
    adminGroup.setName("security-role");
    adminGroup.setType(GroupTypes.TYPE_SECURITY_ROLE);
    identityService.saveGroup(adminGroup);

    identityService.createMembership("user1", "admin");
  }

  @Test
  void addUser2() {
    User user = identityService.newUser("user2");
    user.setFirstName("user");
    user.setLastName("two");
    user.setEmail("user2@example.com");
    identityService.saveUser(user);

    Group adminGroup = identityService.newGroup("assignment");
    adminGroup.setName("assignment");
    adminGroup.setType(GroupTypes.TYPE_ASSIGNMENT);
    identityService.saveGroup(adminGroup);

    identityService.createMembership("user2", "assignment");
  }

  @Test
  void deleteUser() {
    identityService.deleteUser("john.doe");
  }

  @Test
  void test1() {
    Map<String, Object> variables = new HashMap<>();
    variables.put("requestUserId", "张三");
    // 按流程定义 Key 启动
    ProcessInstance processInstance = runtimeService
        .startProcessInstanceByKey("SC_PROCESS", variables);

    log.info("流程实例ID：{}", processInstance.getId());
  }

  private void debugExtensionElements(SequenceFlow flow) {
    System.out.println("=== 连线 [" + flow.getName() + "] 的扩展元素 ===");
    Map<String, List<ExtensionElement>> extensions = flow.getExtensionElements();

    if (extensions == null || extensions.isEmpty()) {
      System.out.println("无扩展元素");
      return;
    }

    for (Map.Entry<String, List<ExtensionElement>> entry : extensions.entrySet()) {
      System.out.println("Key: " + entry.getKey());
      for (ExtensionElement element : entry.getValue()) {
        System.out.println("  ElementText: " + element.getElementText());
        System.out.println("  Namespace: " + element.getNamespace());
        System.out.println("  Attributes: " + element.getAttributes());
        System.out.println("  ChildElements: " + element.getChildElements());
      }
    }
  }

  /**
   * 获取 SequenceFlow 的表单引用信息
   */
  public void getSequenceFlowWithFormKey(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
    UserTask userTask = (UserTask) bpmnModel.getFlowElement(task.getTaskDefinitionKey());

    //getAllSequenceFlowFormProperties(task.getProcessDefinitionId());

    List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();

    for (SequenceFlow flow : outgoingFlows) {
      SequenceFlowInfo info = new SequenceFlowInfo();
      info.setId(flow.getId());
      info.setName(flow.getName());
      info.setSourceRef(flow.getSourceRef());
      info.setTargetRef(flow.getTargetRef());
      info.setCondition(flow.getConditionExpression());
      info.setSkipExpression(flow.getSkipExpression());
      // 获取表单引用 - 从扩展元素中提取
      String formKey = getFormKeyFromExtension(flow);
      info.setFormKey(formKey);

      System.out.println("连线: " + flow.getName() + ", 表单引用: " + formKey);
      getSequenceFlowFormInfo(task.getProcessDefinitionId(), flow.getId());
    }
  }

  /**
   * 根据 sequenceFlowId 获取 formId 和 formName
   */
  public Map<String, String> getSequenceFlowFormInfo(String processDefinitionId, String sequenceFlowId) {
    Map<String, String> result = new HashMap<>();

    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
    SequenceFlow sequenceFlow = (SequenceFlow) bpmnModel.getFlowElement(sequenceFlowId);

    if (sequenceFlow == null) {
      return result;
    }

    // Flowable 命名空间
    String flowableNamespace = "http://flowable.org/bpmn";

    // 获取 flowable:formId
    String formId = sequenceFlow.getAttributeValue(flowableNamespace, "formId");
    // 获取 flowable:formName
    String formName = sequenceFlow.getAttributeValue(flowableNamespace, "formName");

    if (formId != null) {
      result.put("formId", formId);
    }
    if (formName != null) {
      result.put("formName", formName);
    }

    System.out.println("SequenceFlow: " + sequenceFlowId);
    System.out.println("  formId: " + formId);
    System.out.println("  formName: " + formName);

    return result;
  }

  /**
   * 从扩展元素中提取 formKey
   */
  private String getFormKeyFromExtension(SequenceFlow sequenceFlow) {
    Map<String, List<ExtensionElement>> extensionElements = sequenceFlow.getExtensionElements();

    if (extensionElements == null || extensionElements.isEmpty()) {
      return null;
    }

    // 方式1: 直接查找 formKey 扩展元素
    List<ExtensionElement> formKeyElements = extensionElements.get("formKey");
    if (formKeyElements != null && !formKeyElements.isEmpty()) {
      return formKeyElements.get(0).getElementText();
    }

    // 方式2: 查找 properties 中的 formKey
    List<ExtensionElement> properties = extensionElements.get("properties");
    if (properties != null) {
      for (ExtensionElement prop : properties) {
        List<ExtensionElement> propertyList = prop.getChildElements().get("property");
        if (propertyList != null) {
          for (ExtensionElement property : propertyList) {
            String name = property.getAttributeValue(null, "name");
            if ("formKey".equals(name) || "表单引用".equals(name)) {
              return property.getAttributeValue(null, "value");
            }
          }
        }
      }
    }

    // 方式3: 遍历所有扩展元素查找
    for (Map.Entry<String, List<ExtensionElement>> entry : extensionElements.entrySet()) {
      for (ExtensionElement element : entry.getValue()) {
        // 检查属性
        Map<String, List<ExtensionAttribute>> attributes = element.getAttributes();
        if (attributes != null) {
          for (List<ExtensionAttribute> attrList : attributes.values()) {
            for (ExtensionAttribute attr : attrList) {
              if ("formKey".equals(attr.getName())) {
                return attr.getValue();
              }
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * 获取指定 SequenceFlow 的表单属性
   */
  public Map<String, String> getSequenceFlowFormProperties(String processDefinitionId, String sequenceFlowId) {
    Map<String, String> result = new HashMap<>();

    // 获取 BPMN 模型
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
    if (bpmnModel == null) {
      return result;
    }

    // 获取指定的 SequenceFlow
    FlowElement flowElement = bpmnModel.getMainProcess().getFlowElement(sequenceFlowId);

    if (flowElement instanceof SequenceFlow) {
      SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
      // 获取所有扩展属性
      Map<String, List<ExtensionElement>> extensionElements = sequenceFlow.getExtensionElements();

      if (extensionElements != null && !extensionElements.isEmpty()) {
        // 获取 formId 元素
        List<ExtensionElement> formIdElements = extensionElements.get("formId");
        if (formIdElements != null && !formIdElements.isEmpty()) {
          result.put("formId", formIdElements.get(0).getElementText());
        }

        // 获取 formName 元素
        List<ExtensionElement> formNameElements = extensionElements.get("formName");
        if (formNameElements != null && !formNameElements.isEmpty()) {
          result.put("formName", formNameElements.get(0).getElementText());
        }
      }
    }

    return result;
  }

  /**
   * 获取流程中所有 SequenceFlow 的表单属性
   */
  public Map<String, Map<String, String>> getAllSequenceFlowFormProperties(String processDefinitionId) {
    Map<String, Map<String, String>> result = new HashMap<>();

    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
    if (bpmnModel == null) {
      return result;
    }

    // 获取所有流程元素
    Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();

    flowElements.forEach(flowElement -> {
      if (flowElement instanceof SequenceFlow) {
        SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
        Map<String, String> properties = getSequenceFlowFormProperties(processDefinitionId, sequenceFlow.getId());

        if (!properties.isEmpty()) {
          result.put(sequenceFlow.getId(), properties);
        }
      }
    });
    return result;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class SequenceFlowInfo {
    private String id;           // 连线ID
    private String name;         // 连线名称 (如: "分配处理人")
    private String sourceRef;    // 源节点ID
    private String targetRef;    // 目标节点ID
    private String condition;    // 条件表达式
    private String formKey;         // 表单引用 (如: askLeaveForm)
    private Boolean defaultFlow;    // 是否默认流
    private String skipExpression;  // 跳过表达式
  }

  @Test
  void test2() {
    List<Task> taskList = taskService.createTaskQuery().taskAssignee("test1").list();
    taskList.forEach(task -> {
      log.info("任务ID：{}", task.getId());
      log.info("任务名称：{}", task.getName());
      log.info("任务创建时间：{}", task.getCreateTime());
      log.info("任务办理人：{}", task.getAssignee());
      getSequenceFlowWithFormKey(task.getId());
    });
  }


}
