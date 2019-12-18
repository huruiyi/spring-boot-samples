package com.example.Unfiled;


import com.example.Model.Greeting;

import com.example.Model.Quote;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Test_Rest {

    private static final Logger log = LoggerFactory.getLogger(Test_Rest.class);

    @Test
    public void Test1() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }

    public void Test2() {
        RestTemplate restTemplate = new RestTemplate();
        Greeting greeting = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
        log.info(greeting.toString());
    }
}