package com.example.utils;

import java.util.List;

public class PageData<T> {

  public int currentPage;

  public int pageSize;

  public long totalPage;

  public List<T> data;

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(long totalPage) {
    this.totalPage = totalPage;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
