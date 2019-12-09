package com.example.SpringJPA.product.service.impl;

import com.example.SpringJPA.product.entity.Product;
import com.example.SpringJPA.product.entity.ProductComment;
import com.example.SpringJPA.product.repository.ProductCommentRepository;
import com.example.SpringJPA.product.repository.ProductRepository;
import com.example.SpringJPA.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected ProductCommentRepository productCommentRepository;

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
