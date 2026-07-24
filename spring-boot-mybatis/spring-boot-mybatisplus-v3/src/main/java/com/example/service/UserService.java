package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.User;
import java.util.List;

public interface UserService extends IService<User> {

  void saveBatchByNative(List<User> list);

  boolean saveBatchCustom(List<User> list);

  void jdbcBatchInsert(List<User> list, final int batchSize);
}
