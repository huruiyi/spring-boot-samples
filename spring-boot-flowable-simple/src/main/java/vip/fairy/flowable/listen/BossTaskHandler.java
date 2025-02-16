package vip.fairy.flowable.listen;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class BossTaskHandler implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("老板");
    System.out.println("执行boss监听器————————————————");
  }

}
