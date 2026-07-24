package vip.fairy.api.hello;

import static vip.fairy.config.ApiProperties.API_PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

  @Bean
  public RouterFunction<ServerResponse> helloRoutes(HelloHandler handler) {
    return RouterFunctions.route(
            RequestPredicates.GET(API_PREFIX + "/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
            handler::hello)
        .andRoute(
            RequestPredicates.GET(API_PREFIX + "/hello-admin")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
            handler::helloAdmin
        );
  }
}
