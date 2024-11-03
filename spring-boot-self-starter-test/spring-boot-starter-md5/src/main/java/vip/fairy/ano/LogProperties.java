package vip.fairy.ano;


import org.springframework.boot.context.properties.ConfigurationProperties;

//自动注入application配置文件中已log.switch开头的配置参数
@ConfigurationProperties("log.switch")
public class LogProperties {

  //是否启用打印日志功能
  private Boolean enabled = false;

  //是否打印调用者ip
  private Boolean printIp = false;

  //是否打印调用者url
  private Boolean printUrl = false;


  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getPrintIp() {
    return printIp;
  }

  public void setPrintIp(Boolean printIp) {
    this.printIp = printIp;
  }

  public Boolean getPrintUrl() {
    return printUrl;
  }

  public void setPrintUrl(Boolean printUrl) {
    this.printUrl = printUrl;
  }

}
