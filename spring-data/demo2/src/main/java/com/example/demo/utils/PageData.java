package com.example.demo.utils;

import lombok.Data;
import org.hibernate.internal.util.collections.SingletonIterator;

import java.util.List;

@Data
public class PageData<T> {

    public int currentPage;

    public int pageSize;

    public long totalPage;

    public List<T> data;
}
