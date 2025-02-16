package vip.fairy.flowable.model;

/**
 * @ 审批列表查询结果
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskVO {

  private String id;
  private String day;
  private String name;
  private String processInstanceId;
  private String processDefinitionId;
}
