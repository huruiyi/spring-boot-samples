package com.example.demo0.product.service;

import com.example.demo0.product.entity.Product;
import com.example.demo0.product.entity.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

  /**
   * 获取商品配置的分页数据
   *
   * @param pageable 分页参数
   * @return 分页数据
   */
  Page<Product> getPage(Pageable pageable);

  /**
   * 加载指定的商品配置
   *
   * @param id 商品配置ID
   * @return
   */
  Product load(Long id);

  /**
   * 加载指定商品的评论列表
   *
   * @param productId
   * @return
   */
  List<ProductComment> findAllByProduct(Long productId);


  /**
   * 加载所有商品
   *
   * @return
   */
  List<Product> findAll();

}
