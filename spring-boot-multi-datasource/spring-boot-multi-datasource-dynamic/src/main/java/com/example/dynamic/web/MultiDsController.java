package com.example.dynamic.web;

import com.example.dynamic.service.MultiDsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MultiDsController {

    private final MultiDsService multiDsService;

    public MultiDsController(MultiDsService multiDsService) {
        this.multiDsService = multiDsService;
    }

    @GetMapping("/all-data")
    public Map<String, Object> getAllData() {
        return multiDsService.getAllData();
    }

    @GetMapping("/orders")
    public Object getOrders() {
        return multiDsService.getOrdersFromMaster();
    }
}
