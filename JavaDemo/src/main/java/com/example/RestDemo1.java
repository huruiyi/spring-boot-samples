package com.example;


import com.example.model.Greeting;

import com.example.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class RestDemo1 {

    private static final Logger log = LoggerFactory.getLogger(RestDemo1.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());


        Greeting greeting = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
        log.info(greeting.toString());
    }
}