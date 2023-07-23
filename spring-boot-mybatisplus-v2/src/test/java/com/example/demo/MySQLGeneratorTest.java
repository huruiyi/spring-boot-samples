package com.example.demo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity.Builder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class MySQLGeneratorTest {

  /**
   * 数据源配置
   */
  private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
      .Builder("jdbc:mysql://localhost:3306/world?serverTimezone=Asia/Shanghai", "root", "root")
      .build();

  public static void main(String[] args) throws IOException {
    AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);

    File directory = new File("spring-boot-mybatisplus-v2");

    StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
    Builder builder = strategyConfigBuilder.entityBuilder();
    builder.enableFileOverride();
    builder.enableActiveRecord();
    builder.enableLombok();
    builder.naming(NamingStrategy.no_change);
    builder.columnNaming(NamingStrategy.no_change);

    GlobalConfig.Builder globalConfigbuilder = new GlobalConfig.Builder();
    globalConfigbuilder.author("huruiyi");
    globalConfigbuilder.dateType(DateType.ONLY_DATE);
    globalConfigbuilder.commentDate("yyyy-MM-dd HH:ss:mm");
    globalConfigbuilder.outputDir(directory.getCanonicalPath() + "/src/main/java");
    globalConfigbuilder.disableOpenDir();

    PackageConfig.Builder packageBuilder = new PackageConfig.Builder();
    packageBuilder.parent("com.example.demo");
    packageBuilder.moduleName("world");
    packageBuilder.entity("entity");
    packageBuilder.service("service");
    packageBuilder.serviceImpl("service.impl");
    packageBuilder.mapper("mapper");
    packageBuilder.controller("controller");
    packageBuilder.pathInfo(Collections.singletonMap(OutputFile.xml,
        directory.getCanonicalPath() + "/src/main/resources/mapper/" + packageBuilder.build().getModuleName() + "/"));

    generator.packageInfo(packageBuilder.build());
    generator.strategy(strategyConfigBuilder.build());
    generator.global(globalConfigbuilder.build());
    generator.execute();
  }


}
