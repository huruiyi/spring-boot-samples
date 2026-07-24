package com.example.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 数据源2：用户库（db_user）
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.jpa.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class Db2Config {

    @Bean("db2DataSource")
    @ConfigurationProperties(prefix = "app.datasource.db2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db2DataSource") DataSource ds) {
        return builder
                .dataSource(ds)
                .packages("com.example.jpa.db2")
                .persistenceUnit("db2")
                .build();
    }

    @Bean("db2TransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
