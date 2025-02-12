package vip.fairy.api.auth;

import static vip.fairy.config.ApiProperties.AUTH_API_PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthenticationRouter {

  @Bean
  public RouterFunction<ServerResponse> authRoutes(AuthenticationHandler handler) {
    RequestPredicate requestPredicate = RequestPredicates.accept(MediaType.TEXT_PLAIN);
    return RouterFunctions.route(RequestPredicates.GET(AUTH_API_PREFIX + "/login").and(requestPredicate), handler::login);
  }

}
