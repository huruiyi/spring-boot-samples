package com.example.demo1.repository;


import com.example.demo1.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {


}
