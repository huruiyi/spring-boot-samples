package com.example.demoK.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    includeFilters = {
        @ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = {"com.example.Spring.demoK.*Dao", "com.example.Spring.demoK.*Impl"})
    },
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            classes = {org.springframework.stereotype.Controller.class})}
)
public class SequenceGeneratorConfiguration {

}