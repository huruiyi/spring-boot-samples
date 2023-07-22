package com.example.demo.service;

import com.example.demo.entity.File;
import com.example.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FileServiceImpl implements FileService {

  @Autowired
  public FileRepository fileRepository;

  @Override
  public File saveFile(File file) {
    return fileRepository.save(file);
  }

  @Override
  public void removeFile(String id) {
    fileRepository.deleteById(id);
  }

  @Override
  public Optional<File> getFileById(String id) {
    return fileRepository.findById(id);
  }

  @Override
  public List<File> listFilesByPage(int pageIndex, int pageSize) {
    Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Direction.DESC, "uploadDate"));
    Page<File> page = fileRepository.findAll(pageable);
    List<File> list = page.getContent();
    return list;
  }
}
