package com.example.bean;

import java.util.HashMap;
import java.util.List;

public class ExcelReadResponse<T> {

    private List<T> result;
    private List<HashMap<Integer, String>> message;
}
