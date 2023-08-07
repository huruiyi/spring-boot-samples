package com.example.repository;

import com.example.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {

}
