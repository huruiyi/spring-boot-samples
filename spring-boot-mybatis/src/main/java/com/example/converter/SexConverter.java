package com.example.converter;

import com.example.enums.SexEnum;
import javax.persistence.AttributeConverter;

public class SexConverter implements AttributeConverter<SexEnum, Integer> {

  // 将枚举转换为数据库列
  @Override
  public Integer convertToDatabaseColumn(SexEnum sex) {
    return sex.getId();
  }

  // 将数据库列转换为枚举
  @Override
  public SexEnum convertToEntityAttribute(Integer id) {
    return SexEnum.getEnumById(id);
  }

}
