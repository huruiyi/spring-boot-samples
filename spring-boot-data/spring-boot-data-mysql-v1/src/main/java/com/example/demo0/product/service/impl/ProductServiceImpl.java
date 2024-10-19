package com.example.demo0.product.service.impl;


import com.example.demo0.product.entity.Product;
import com.example.demo0.product.entity.ProductComment;
import com.example.demo0.product.repository.ProductCommentRepository;
import com.example.demo0.product.repository.ProductRepository;
import com.example.demo0.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  protected final ProductRepository productRepository;
  protected final ProductCommentRepository productCommentRepository;

  public ProductServiceImpl(ProductRepository productRepository, ProductCommentRepository productCommentRepository) {
    this.productRepository = productRepository;
    this.productCommentRepository = productCommentRepository;
  }

  @Override
  public Page<Product> getPage(Pageable pageable) {
    return this.productRepository.findAll(pageable);
  }

  @Override
  public Product load(Long id) {
    return this.productRepository.findById(id).get();
  }

  @Override
  public List<ProductComment> findAllByProduct(Long productId) {

    return this.productCommentRepository.findByProductIdOrderByCreated(productId);
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
