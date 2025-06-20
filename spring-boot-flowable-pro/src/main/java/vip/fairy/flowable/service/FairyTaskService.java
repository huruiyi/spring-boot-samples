package vip.fairy.flowable.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.fairy.flowable.model.TaskVO;

@Service
public class FairyTaskService {

  @Autowired
  private TaskService taskService;

  public List<TaskVO> getTaskList(String candidateGroup) {
    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).list();
    return tasks.stream().map(task -> {
      Map<String, Object> variables = taskService.getVariables(task.getId());
      return TaskVO.builder()
          .id(task.getId())
          .day(variables.get("day").toString())
          .name(variables.get("studentName").toString())
          .processDefinitionId(task.getProcessDefinitionId())
          .processInstanceId(task.getProcessInstanceId())
          .build();
    }).collect(Collectors.toList());
  }

}
