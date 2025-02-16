package vip.fairy.flowable.listen;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

@Component
public class ManagerTaskHandler implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("经理");
    System.out.println("执行经理监听器——————————————————————");
  }

}
