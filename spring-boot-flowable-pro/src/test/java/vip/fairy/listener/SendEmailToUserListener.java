package vip.fairy.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;


@Slf4j
@Data
public class SendEmailToUserListener implements ExecutionListener {

  private Expression to;
  private Expression tempKey;

  @Override
  public void notify(DelegateExecution delegateExecution) {
    log.info("SendEmailToUserListener");
    delegateExecution.getVariable(to.getExpressionText());
    delegateExecution.getVariable(tempKey.getExpressionText());
  }

}
