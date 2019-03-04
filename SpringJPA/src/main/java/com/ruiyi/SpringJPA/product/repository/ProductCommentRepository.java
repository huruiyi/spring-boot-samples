package com.ruiyi.SpringJPA.product.repository;

import com.ruiyi.SpringJPA.product.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    List<ProductComment> findByProductIdOrderByCreated(Long productId);
}
