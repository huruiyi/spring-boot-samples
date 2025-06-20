package vip.fairy.flowable.model;

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
