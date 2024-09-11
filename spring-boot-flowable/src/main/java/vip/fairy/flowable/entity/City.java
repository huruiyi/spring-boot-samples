package vip.fairy.flowable.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class City {

  @Id
  private Long Id;

  private String Name;
  private String CountryCode;
  private String District;
  private Long Population;
}
