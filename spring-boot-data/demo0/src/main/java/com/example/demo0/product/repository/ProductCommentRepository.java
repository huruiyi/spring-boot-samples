package com.example.demo0.product.repository;

import com.example.demo0.product.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

  List<ProductComment> findByProductIdOrderByCreated(Long productId);
}
