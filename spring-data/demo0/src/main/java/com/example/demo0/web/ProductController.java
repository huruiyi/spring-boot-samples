package com.example.demo0.web;


import com.example.demo0.product.entity.Product;
import com.example.demo0.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public List<Product> products() {
        return productService.findAll();
    }
}
