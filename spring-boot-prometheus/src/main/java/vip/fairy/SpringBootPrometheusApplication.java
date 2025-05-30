package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPrometheusApplication {

  /**
   * <a href="http://localhost:8080/actuator/prometheus">prometheus</a>
   *
   * <a href="http://localhost:8080/actuator/metrics">metrics</a>
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringBootPrometheusApplication.class, args);
  }

}
