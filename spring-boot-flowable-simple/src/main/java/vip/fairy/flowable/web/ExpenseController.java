package vip.fairy.flowable.web;

import java.util.HashMap;
import java.util.List;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ExpenseController {

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Autowired
  @Qualifier("processEngine")
  private ProcessEngine processEngine;

  /**
   * 添加报销:<a href="http://localhost:9090/add?userId=zhang&money=123">请假</a>
   *
   * @param userId 用户Id
   * @param money  报销金额
   */
  @ResponseBody
  @RequestMapping(value = "add")
  public String addExpense(String userId, Integer money) {
    //启动流程
    HashMap<String, Object> map = new HashMap<>();
    map.put("userTask", userId);
    map.put("money", money);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave_flow", map);
    //模拟完成任务（只有上一个节点任务完成才会到下一个节点）
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    System.out.println("任务id:" + task.getId());
    taskService.complete(task.getId());
    return "提交成功.流程Id为：" + processInstance.getId();
  }

  /*
   * http://localhost:9090/findTask?userId=%E7%BB%8F%E7%90%86
   */
  @ResponseBody
  @RequestMapping("findTask")
  public String findTask(String userId) {
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery().taskAssignee(userId).list();
    return list.size() + "条";
  }

  /**
   * <a href="http://localhost:9090/list?userId=%E7%BB%8F%E7%90%86">列表</a>
   */
  @ResponseBody
  @RequestMapping(value = "/list")
  public Object list(String userId) {
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
    for (Task task : tasks) {
      Object userTask = taskService.getVariable(task.getId(), "userTask");
      Object money = taskService.getVariable(task.getId(), "money");
      System.out.println(task + ",processId:" + task.getProcessInstanceId() + " ,userTask:" + userTask + " ,money:" + money);
    }
    return tasks.toString();
  }

  @ResponseBody
  @RequestMapping(value = "apply")
  public String apply(String taskId) {
    List<Task> t = taskService.createTaskQuery().list();
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

    HashMap<String, Object> map = new HashMap<>();
    map.put("result", "同意");
    taskService.complete(taskId, map);
    return "processed ok!";
  }

  @ResponseBody
  @RequestMapping(value = "reject")
  public String reject(String taskId) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("result", "驳回");
    taskService.complete(taskId, map);
    return "reject";
  }


}
