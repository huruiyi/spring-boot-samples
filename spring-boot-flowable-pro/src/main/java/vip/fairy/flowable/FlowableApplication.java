package vip.fairy.flowable;

import javax.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowableApplication implements CommandLineRunner {

  public final EntityManager entityManager;

  public FlowableApplication(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * <a href="http://localhost:18080/druid">druid</a>
   * <a href="http://localhost:18080/idm">6.8.1</a>
   * <a href="http://localhost:18080/modeler"></a>
   */
  public static void main(String[] args) {
    SpringApplication.run(FlowableApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    entityManager.createQuery("select Id, Name, CountryCode, District, Population from City ").getHints();
  }


}
