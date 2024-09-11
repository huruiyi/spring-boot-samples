package vip.fairy.flowable.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;
import java.io.InputStream;
import java.io.OutputStream;
import vip.fairy.flowable.model.Result;
import vip.fairy.flowable.model.TaskVO;
import vip.fairy.flowable.service.FairyTaskService;


@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController {

  private final RuntimeService runtimeService;

  private final TaskService taskService;

  private final RepositoryService repositoryService;

  private final ProcessEngine processEngine;

  private FairyTaskService fairyTaskService;

  public LeaveController(RuntimeService runtimeService, TaskService taskService, RepositoryService repositoryService, ProcessEngine processEngine,
      FairyTaskService fairyTaskService) {
    this.runtimeService = runtimeService;
    this.taskService = taskService;
    this.repositoryService = repositoryService;
    this.processEngine = processEngine;
    this.fairyTaskService = fairyTaskService;
  }

  @PostMapping("add/{day}/{studentUser}")
  public Result sub(@PathVariable("day") Integer day, @PathVariable("studentUser") String studentUser) {
    //  学生提交请假申请
    Map<String, Object> map = new HashMap<>();
    map.put("day", day);
    map.put("studentName", studentUser);
    //  stuLeave为学生请假流程xml文件中的id，即后面bpmn20.xml文件的process  id="stuLeave"
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("stuLeave", map);
    log.info("流程实例ID：{}", processInstance.getId());
    //完成申请任务
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
    //此处id为流程id
    return new Result(true, "提交成功.流程Id为：" + processInstance.getId());
  }

  @GetMapping("teacherList")
  public Result teacherList() {
    //此处.taskCandidateGroup("a")的值“a”即是画流程图时辅导员审批节点"分配用户-候选组"中填写的值
    List<TaskVO> taskVOList = fairyTaskService.getTaskList("a");
    log.info("任务列表：{}", taskVOList);
    if (taskVOList.isEmpty()) {
      return new Result(false, "没有任务");
    }
    return new Result(true, "获取成功", taskVOList);
  }

  /**
   * 辅导员批准
   *
   * @param taskId 任务ID，非流程id
   */
  @GetMapping("teacherApply/{taskId}")
  public Result teacherApply(@PathVariable("taskId") String taskId) {
    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("a").list();
    Task task = taskService.createTaskQuery().taskCandidateGroup("a").taskId(taskId).singleResult();
    if (task == null) {
      return new Result(false, "没有任务");
    }
    //通过审核
    HashMap<String, Object> map = new HashMap<>();
    map.put("outcome", "通过");
    taskService.complete(task.getId(), map);
    return new Result(true, "审批成功");
  }

  /**
   * 辅导员拒绝
   *
   * @param taskId 任务ID，非流程id
   */
  @GetMapping("teacherReject/{taskId}")
  public Result teacherReject(@PathVariable("taskId") String taskId) {
    Task task1 = taskService.createTaskQuery().taskCandidateGroup("a").taskId(taskId).singleResult();
    //Task  task  =  taskService.createTaskQuery().taskId(taskId).singleResult();
    if (task1 == null) {
      return new Result(false, "没有任务");
    }
    //通过审核
    HashMap<String, Object> map = new HashMap<>();
    map.put("outcome", "驳回");
    taskService.complete(task1.getId(), map);
    return new Result(true, "审批失败");
  }

  /**
   * 院长获取审批管理列表
   */
  @GetMapping("deanList")
  public Result deanList() {
    //此处.taskCandidateGroup("b")的值“b”即是画流程图时辅导员审批节点"分配用户-候选组"中填写的值
    List<TaskVO> taskVOList = fairyTaskService.getTaskList("b");
    if (taskVOList.isEmpty()) {
      return new Result(false, "没有任务");
    }
    return new Result(true, "获取成功", taskVOList);
  }

  /**
   * 院长批准
   *
   * @param taskId 任务ID，非流程id
   */
  @GetMapping("deanApply/{taskId}")
  public Result apply(@PathVariable("taskId") String taskId) {
    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("b").list();
    //Task  task  =  taskService.createTaskQuery().taskId(taskId).singleResult();
    if (tasks == null) {
      return new Result(false, "没有任务");
    }
    //通过审核
    HashMap<String, Object> map = new HashMap<>();
    map.put("outcome", "通过");
    for (Task task : tasks) {
      taskService.complete(task.getId(), map);
    }
    return new Result(true, "审批成功");
  }

  /**
   * 院长拒绝
   *
   * @param taskId 任务ID，非流程id
   */
  @GetMapping("deanReject/{taskId}")
  public Result deanReject(@PathVariable("taskId") String taskId) {
    //   List<Task>  tasks  =  taskService.createTaskQuery().taskCandidateGroup("b").list();
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      return new Result(false, "没有任务");
    }
    //通过审核
    HashMap<String, Object> map = new HashMap<>();
    map.put("outcome", "驳回");
    taskService.complete(task.getId(), map);
    return new Result(true, "审批成功");
  }

  /**
   * 再次申请
   *
   * @param piId 流程id
   */
  @GetMapping("subAgain/{piId}/{day}")
  public Result subAgain(@PathVariable("piId") String piId, @PathVariable("day") Integer day) {
    Task task = taskService.createTaskQuery().processInstanceId(piId).singleResult();
    if (Objects.isNull(task)) {
      return new Result(false, "没有任务");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("day", day);
    taskService.complete(task.getId(), map);
    return new Result(true, "申请成功");
  }

  /**
   * 生成流程图
   *
   * @param taskId 流程ID
   */
  @GetMapping("processDiagram/{taskId}")
  public void genProcessDiagram(HttpServletResponse httpServletResponse, @PathVariable("taskId") String taskId) throws Exception {
    ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(taskId).singleResult();

    //流程走完的不显示图
    if (pi == null) {
      return;
    }
    Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
    //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
    String InstanceId = task.getProcessInstanceId();
    List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(InstanceId).list();

    //得到正在执行的Activity的Id
    List<String> activityIds = new ArrayList<>();
    List<String> flows = new ArrayList<>();
    for (Execution exe : executions) {
      List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
      activityIds.addAll(ids);
    }

    //获取流程图
    BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
    ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
    ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
    InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(),
        engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
    OutputStream out = null;
    byte[] buf = new byte[1024];
    int legth = 0;
    try {
      //此处设置resp的header诸多文章都没写，但是我不写出不来流程图，诸位可去掉试试
      httpServletResponse.setHeader("Content-Type", "image/png;charset=utf-8");
      out = httpServletResponse.getOutputStream();
      while ((legth = in.read(buf)) != -1) {
        out.write(buf, 0, legth);
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }


}
