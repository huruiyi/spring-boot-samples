package com.example.Unfiled;

import com.example.DataSourcePool.MyDbPool;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DataBasePool {


  @Test
  public void CustomDataPool() {
    Connection connection = MyDbPool.getConnection();
    System.out.println(connection);
    MyDbPool.AddBack(connection);
    System.out.println("数据池的容量：" + MyDbPool.GetCapacity());
  }
}
