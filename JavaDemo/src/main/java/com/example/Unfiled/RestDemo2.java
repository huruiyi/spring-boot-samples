package com.example.Unfiled;


import com.example.Model.Greeting;
import com.example.Model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestDemo2 {

    private static final Logger log = LoggerFactory.getLogger(RestDemo2.class);

    public static void main(String args[]) {
        SpringApplication.run(RestDemo2.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner RestTemplateDemo0(RestTemplate restTemplate) {
        return args -> {
            Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            System.out.println(quote.toString());
        };
    }

    @Bean
    public long RestTemplateDemo1() {
        RestTemplate restTemplate = new RestTemplate();
        Greeting res = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
        System.out.println(res);
        return 0;
    }
}
