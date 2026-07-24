package com.example.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 数据源1：订单库（db_order）
 * 关键：@EnableJpaRepositories 限定 basePackages + entityManagerFactoryRef + transactionManagerRef
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.jpa.db1",              // 只扫 db1 包下的 Repository
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class Db1Config {

    @Bean("db1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.db1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db1EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db1DataSource") DataSource ds) {
        return builder
                .dataSource(ds)
                .packages("com.example.jpa.db1")           // 只扫描 db1 包下的 Entity
                .persistenceUnit("db1")
                .build();
    }

    @Bean("db1TransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("db1EntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
