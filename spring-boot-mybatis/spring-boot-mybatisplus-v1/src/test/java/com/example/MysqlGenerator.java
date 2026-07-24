package com.example;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class MysqlGenerator {

  public static String scanner(String tip) throws MybatisPlusException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入" + tip + "：");
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  public static void main(String[] args) throws RuntimeException, IOException {
    AutoGenerator mpg = new AutoGenerator();
    GlobalConfig gc = new GlobalConfig();

    File directory = new File("spring-boot-mybatisplus");

    String canonicalPath = directory.getCanonicalPath();
    gc.setOutputDir(canonicalPath + "/src/main/java");
    gc.setAuthor("fairy.vip");
    gc.setOpen(false);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl("jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
    // dsc.setSchemaName("public");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("test");
    dsc.setPassword("test");
    mpg.setDataSource(dsc);

    // 包配置
    final PackageConfig pc = new PackageConfig();
    pc.setModuleName(scanner("Module name"));
    //pc.setModuleName("world");
    pc.setParent("com.example");
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    // String templatePath = "/templates/mapper.xml.vm";

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出

    focList.add(new FileOutConfig(templatePath) {

      public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
        return canonicalPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    //strategy.setSuperEntityClass("com.example.demo.base.BaseEntity");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    //strategy.setSuperControllerClass("com.example.demo.base.BaseController");

    //表名
    strategy.setInclude("country", "countrylanguage","city");
    strategy.setSuperEntityColumns("id");
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

}
