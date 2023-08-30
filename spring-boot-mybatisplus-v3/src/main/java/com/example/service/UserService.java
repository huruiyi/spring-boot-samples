package com.example.service;

import com.example.model.User;
import java.util.List;

public interface UserService {

  boolean saveBatchByNative(List<User> list);

  boolean saveBatchCustom(List<User> list);

}
