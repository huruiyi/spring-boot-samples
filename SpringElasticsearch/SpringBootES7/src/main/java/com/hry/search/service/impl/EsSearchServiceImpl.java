package com.hry.search.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import com.hry.search.mapping.ProductDocument;
import com.hry.search.repository.ProductDocumentRepository;
import com.hry.search.service.EsSearchService;

@Service
public class EsSearchServiceImpl extends BaseSearchServiceImpl<ProductDocument> implements EsSearchService {

	@Resource
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Resource
	private ProductDocumentRepository productDocumentRepository;

	@Override
	public void save(ProductDocument... productDocuments) {
		elasticsearchRestTemplate.putMapping(ProductDocument.class);
		if (productDocuments.length > 0) {
			productDocumentRepository.saveAll(Arrays.asList(productDocuments));
		}
	}

	@Override
	public void delete(String id) {
		productDocumentRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		productDocumentRepository.deleteAll();
	}

	@Override
	public ProductDocument getById(String id) {
		return productDocumentRepository.findById(id).get();
	}

	@Override
	public List<ProductDocument> getAll() {
		List<ProductDocument> list = new ArrayList<>();
		productDocumentRepository.findAll().forEach(list::add);
		return list;
	}

}
