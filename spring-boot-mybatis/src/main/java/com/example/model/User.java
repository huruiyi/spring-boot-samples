package com.example.model;


import com.example.converter.SexConverter;
import com.example.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "mybatis_user")
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String userName;

  @Convert(converter = SexConverter.class)
  private SexEnum sex;

  private String mobile;

  private String email;

  private String note;

  @Column(name = "position_id")
  private Integer positionId;

  @Transient
  private List<String> hobbies;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "login_time")
  private LocalDateTime loginTime;
}
