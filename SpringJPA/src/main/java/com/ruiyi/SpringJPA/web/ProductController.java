package com.ruiyi.SpringJPA.web;


import com.ruiyi.SpringJPA.product.dto.ProductDto;
import com.ruiyi.SpringJPA.product.entity.Product;
import com.ruiyi.SpringJPA.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
