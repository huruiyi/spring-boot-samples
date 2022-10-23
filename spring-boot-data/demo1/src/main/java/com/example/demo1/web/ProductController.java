package com.example.demo1.web;

import com.example.demo1.model.Product;
import com.example.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Product")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    private Product createProject() {
        Product product = new Product();
        product.setName("荣耀V30");
        product.setCode("2601010170308");
        product.setTitle("荣耀V30 性能强悍，拍照很清晰");
        product.setDescription("荣耀V30 双模5G 麒麟990 突破性相机矩阵 8GB+128GB 曙光之橙 李现同款");
        product.setImgUrl("mobile.jpg");
        product.setPrice(3099.00);
        product.setProductCategoryName("荣耀V系列");
        return product;
    }


    @RequestMapping(value = "/save")
    public String save() {
        Product product = productRepository.save(createProject());
        System.out.println(product);
        return product.toString();
    }


    @RequestMapping(value = "/findAll")
    public String findAll() {
        productRepository.save(createProject());
        List<Product> productList = productRepository.findAll();
        System.out.println(productList);
        return productList.toString();
    }


    @RequestMapping(value = "/findByProductCategoryName")
    public String findByProductCategoryName() {
        Product product = createProject();
        productRepository.save(product);
        List<Product> productList = productRepository.findByProductCategoryName(product.getProductCategoryName());
        System.out.println(productList);
        return productList.toString();
    }


}
