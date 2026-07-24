package com.example.jpa.web;

import com.example.jpa.service.MultiDsService;
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
}
