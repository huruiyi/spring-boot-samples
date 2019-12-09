package com.example.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.Date;

public class TimeOnlyTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setTime(i, new Time(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        java.sql.Date date = rs.getDate(columnName);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        java.sql.Time sqlTime = rs.getTime(columnIndex);
        if (sqlTime != null) {
            return new Date(sqlTime.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        java.sql.Time sqlTime = cs.getTime(columnIndex);
        if (sqlTime != null) {
            return new Date(sqlTime.getTime());
        }
        return null;
    }

}
