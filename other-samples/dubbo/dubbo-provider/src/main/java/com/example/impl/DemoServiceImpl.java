package com.example.impl;

import com.example.DubboRemoteService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

//@Service(version = "${dubbo.global.version}", timeout = 1000)
@DubboService(version = "${dubbo.global.version}", timeout = 1000)
public class DemoServiceImpl implements DubboRemoteService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String call(String name) {
        return String.format("Hello! I am %s, Nice to meet you!!!", name);
    }
}
