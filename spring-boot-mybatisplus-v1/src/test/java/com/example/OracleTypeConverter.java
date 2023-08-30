package com.example;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public class OracleTypeConverter implements ITypeConvert {

  public OracleTypeConverter() {
  }

  public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
    String t = fieldType.toLowerCase();
    if (t.contains("char")) {
      return DbColumnType.STRING;
    }
    if (!t.contains("date") && !t.contains("timestamp")) {
      if (t.contains("number")) {
        if (t.matches("number\\(+\\d\\)")) {
          return DbColumnType.INTEGER;
        }

        if (t.matches("number\\(+\\d{2}+\\)")) {
          return DbColumnType.LONG;
        }

        return DbColumnType.INTEGER;//默认返回int类型
      }

      if (t.contains("float")) {
        return DbColumnType.FLOAT;
      }

      if (t.contains("clob")) {
        return DbColumnType.STRING;
      }

      if (t.contains("blob")) {
        return DbColumnType.BLOB;
      }

      if (t.contains("binary")) {
        return DbColumnType.BYTE_ARRAY;
      }

      if (t.contains("raw")) {
        return DbColumnType.BYTE_ARRAY;
      }
    } else {
      switch (globalConfig.getDateType()) {
        case ONLY_DATE:
          return DbColumnType.DATE;
        case SQL_PACK:
          return DbColumnType.TIMESTAMP;
        case TIME_PACK:
          return DbColumnType.LOCAL_DATE_TIME;
        default:
          return DbColumnType.TIMESTAMP;
      }
    }
    return DbColumnType.STRING;
  }
}
