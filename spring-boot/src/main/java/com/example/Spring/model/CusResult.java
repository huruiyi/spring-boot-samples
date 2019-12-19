package com.example.Spring.model;

import lombok.Data;

import java.util.List;

@Data
public class CusResult {
    private Integer status;
    private String msg;
    private List<Object> data;

    @Override
    public String toString() {
        return "CusResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
