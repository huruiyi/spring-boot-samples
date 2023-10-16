package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SampleDto {

  /**
   * <a href="https://tc39.es/ecma262/">...</a>
   * <a href="https://262.ecma-international.org/14.0/">...</a>
   * <a href="https://262.ecma-international.org/6.0/#sec-date-time-string-format%C2%A0">...</a>
   */
  public static final String DATE_FORMAT_ISO8601 =  "YYYY-MM-DDTHH:mm:ss.sssZ";

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Kolkata")
  private Instant instant;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private Date date;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate localDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime localDateTime;

}
