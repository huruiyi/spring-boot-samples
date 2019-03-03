package com.ruiyi.SpringCloudEurekaClient.product.api;


import com.ruiyi.SpringCloudEurekaClient.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);


    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/")
    public List<Product> products() {

        List<String> services = client.getServices();
        List<ServiceInstance> eurekaClient1 = client.getInstances("EurekaClient1");
        for (ServiceInstance serviceInstance : eurekaClient1) {
            System.out.println(serviceInstance.getUri() + " " + serviceInstance.getPort());
        }
        List<Product> products = new ArrayList<>();

        Product product;
        for (int i = 0; i < 10; i++) {
            product = new Product();
            product.setId(i);
            product.setName("产品" + i);
            product.setPrice(10 + i);
            products.add(product);
        }
        return products;
    }
}
