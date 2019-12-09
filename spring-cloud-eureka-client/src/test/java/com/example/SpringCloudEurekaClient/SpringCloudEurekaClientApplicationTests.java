package com.example.SpringCloudEurekaClient;

import com.example.SpringCloudEurekaClient.product.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudEurekaClientApplicationTests {


    @Autowired
    @Resource(name = "restTemplate")
    RestTemplate restTemplate;


    @Test
    public void contextLoads() {
        Product[] products = restTemplate.getForObject("http://EurekaClient1/products/", Product[].class);
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
