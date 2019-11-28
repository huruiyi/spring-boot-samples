package com.hry.search.repository;

import com.hry.search.mapping.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {
}
