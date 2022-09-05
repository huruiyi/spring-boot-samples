package com.example.Spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class SingerJobResults {
    private static Logger logger = LoggerFactory.getLogger(SingerJobResults.class);

    @Configuration
    static class SingerConfig {

        @Bean
        JdbcTemplate jdbcTemplate(){
            return new JdbcTemplate(dataSource());
        }

        @Bean(destroyMethod = "close")
        public BasicDataSource dataSource() {
            try {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
                dataSource.setUsername("root");
                dataSource.setPassword("root");
                return dataSource;
            } catch (Exception e) {
                logger.error("DBCP DataSource bean cannot be created!", e);
                return null;
            }
        }
    }

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);

        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM test003");
        for (Map<String, Object> result : results) {
            logger.info(result.toString());
        }

        ctx.close();
    }
}
