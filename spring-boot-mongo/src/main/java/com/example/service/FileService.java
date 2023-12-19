package com.example.service;

import com.example.entity.File;

import java.util.List;
import java.util.Optional;

public interface FileService {

  /**
   * 保存文件
   */
  File saveFile(File file);

  /**
   * 删除文件
   */
  void removeFile(String id);

  /**
   * 根据id获取文件
   */
  Optional<File> getFileById(String id);

  /**
   * 分页查询，按上传时间降序
   */
  List<File> listFilesByPage(int pageIndex, int pageSize);
}
