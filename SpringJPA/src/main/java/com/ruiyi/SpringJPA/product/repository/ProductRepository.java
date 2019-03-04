package com.ruiyi.SpringJPA.product.repository;

import com.ruiyi.SpringJPA.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
