package com.example.demo1.web;

import com.example.demo1.model.ProductCategory;
import com.example.demo1.repository.ProductCategoryRepository;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ProductCategory")
public class ProductCategoryController {

  private final ProductCategoryRepository productCategoryRepository;

  public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
    this.productCategoryRepository = productCategoryRepository;
  }


  @RequestMapping(value = "/createCategory")
  public String createCategory() {
    ProductCategory productCategory = createProjectCategory();
    ProductCategory category = productCategoryRepository.save(productCategory);
    return category.toString();
  }


  @RequestMapping(value = "/findAllCategory")
  public String findAllCategory() {
    ProductCategory productCategory = createProjectCategory();
    productCategoryRepository.save(productCategory);
    List<ProductCategory> catagoryList = productCategoryRepository.findAll();
    return catagoryList.toString();
  }


  private ProductCategory createProjectCategory() {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setName("荣耀手机");
    productCategory.setDescription("荣耀手机 性能强悍，拍照很清晰");
    productCategory.setTitle("Mobiles and Tablet");
    productCategory.setImgUrl("mobile.jpg");
    return productCategory;
  }


}
