package com.example.demo0.product.dto;

import com.example.demo0.product.entity.ProductComment;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

public class ProductCommentDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private ProductDto product;
  private String content;
  private Date created;

  public ProductCommentDto() {

  }

  public ProductCommentDto(ProductComment productComment) {
    this.id = productComment.getId();
    this.content = productComment.getContent();
    this.created = productComment.getCreated();
  }

  @Override
  public String toString() {
    return this.toStringHelper().toString();
  }

  protected MoreObjects.ToStringHelper toStringHelper() {
    return MoreObjects.toStringHelper(this)
        .add("id", getId())
        .add("productId", getProduct());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
