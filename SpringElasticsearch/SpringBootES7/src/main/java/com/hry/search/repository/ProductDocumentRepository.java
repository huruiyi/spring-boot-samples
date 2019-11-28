package com.hry.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.hry.search.mapping.ProductDocument;

 
@Component
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {
}
