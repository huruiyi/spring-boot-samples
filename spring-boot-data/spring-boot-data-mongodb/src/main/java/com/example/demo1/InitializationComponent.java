package com.example.demo1;

import com.example.demo1.model.Product;
import com.example.demo1.model.ProductCategory;
import com.example.demo1.repository.ProductCategoryRepository;
import com.example.demo1.repository.ProductRepository;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializationComponent {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Autowired
  private ProductRepository productRepository;


  @PostConstruct
  private void init() {

    logger.info("Start");

    productCategoryRepository.deleteAll();
    productRepository.deleteAll();

    ProductCategory productCategory = new ProductCategory();
    productCategory.setName("Mobile");
    productCategory.setDescription("Mobile phones");
    productCategory.setTitle("Mobiles and Tablet");
    productCategory.setImgUrl("mobile.jpg");
    productCategoryRepository.save(productCategory);

    Product product = new Product();
    product.setName("Kamsung D3");
    product.setCode("KAMSUNG-TRIOS");
    product.setTitle("Kamsung Trios 12 inch , black , 12 px ....");
    product.setDescription("Kamsung Trios 12 inch with Touch");
    product.setImgUrl("kamsung.jpg");
    product.setPrice(12000.00);
    product.setProductCategoryName(productCategory.getName());
    productRepository.save(product);

    product = new Product();
    product.setName("Lokia Pomia");
    product.setCode("LOKIA-POMIA");
    product.setTitle("Lokia 12 inch , white , 14px ....");
    product.setDescription("Lokia Pomia 10 inch with NFC");
    product.setImgUrl("lokia.jpg");
    product.setPrice(9000.00);
    product.setProductCategoryName(productCategory.getName());
    productRepository.save(product);

    logger.info("End");
  }
}
