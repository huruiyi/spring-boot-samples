package com.example;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OracleGenerator {

  private String drivername = "oracle.jdbc.driver.OracleDriver";
  private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/orcl";
  private String username = "c##zjy";
  private String password = "zjy";

  public static void main(String[] args) {
    //需要生产的表
    String[] tables = new String[]{"ENTERPRISE"};
    System.out.println("总表数：" + tables.length);

    OracleGenerator s = new OracleGenerator();
    s.createMapper(tables);


  }

  private void createMapper(String[] tables) {

    AutoGenerator mpg = new AutoGenerator();
    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String relativelyPath = System.getProperty("user.dir") + File.separator;
    String path = StringUtils.replace(relativelyPath, "\\", "/") + "SpringBootMybatisPlus/src/main/java";
    System.out.println("OutputDir=" + path);
    gc.setOutputDir(path);
    gc.setActiveRecord(true);// 开启 activeRecord 模式
    gc.setEnableCache(false);// XML 二级缓存
    gc.setBaseResultMap(true);// XML ResultMap
    gc.setBaseColumnList(true);// XML columList
    gc.setSwagger2(false);
    gc.setAuthor("coscon-liyzh");
    gc.setFileOverride(false);//非覆盖
    gc.setOpen(false);

    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    gc.setMapperName("%sDao");
    //gc.setXmlName("%sDao");
    gc.setServiceName("MP%sService");
    gc.setServiceImplName("%sServiceImpl");
    gc.setControllerName("%sController");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setDbType(DbType.ORACLE);
    dsc.setTypeConvert(new OracleTypeConverter() {//已经自定义了
      // 自定义数据库表字段类型转换【可选】
    });
    dsc.setDriverName(drivername);
    dsc.setUsername(username);
    dsc.setPassword(password);
    dsc.setUrl(jdbcUrl);
    mpg.setDataSource(dsc);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
    strategy.setInclude(tables); // 需要生成的表
    strategy.setSuperControllerClass("com.example.springbootmybatisplus.base.BaseController");
    mpg.setStrategy(strategy);

    // 包配置
    PackageConfig pc = new PackageConfig();
    //pc.setModuleName("core");
    pc.setParent("com.example.springbootmybatisplus");// 自定义包路径
    pc.setController("controller.manage");// 这里是控制器包名，默认 web
    pc.setEntity("model");
    pc.setMapper("dao");
    pc.setXml("model.mapper");
    mpg.setPackageInfo(pc);

    // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
        this.setMap(map);
      }
    };

    mpg.setCfg(cfg);

    mpg.execute();
    // 打印注入设置
    System.err.println(mpg.getCfg().getMap().get("config"));
  }
}
