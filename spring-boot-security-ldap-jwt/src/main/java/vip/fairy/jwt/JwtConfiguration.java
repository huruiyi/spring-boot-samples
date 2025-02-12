package vip.fairy.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
public class JwtConfiguration {

  @Value("${jwt.issuer}")
  private String issuer;

  @Value("${jwt.expiration.seconds}")
  private long expirationSeconds;

  public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

  private final Key secret = Keys.secretKeyFor(SIGNATURE_ALGORITHM);

  @Bean
  public JwtIssuer jwtIssuer() {
    return new JwtIssuer(issuer, secret, expirationSeconds);
  }

  @Bean
  public AuthenticationWebFilter jwtAuthenticationWebFilter(JwtIssuer jwtIssuer) {
    AuthenticationWebFilter jwtAuthFilter = new AuthenticationWebFilter(new JwtAuthenticationManager(jwtIssuer));
    jwtAuthFilter.setServerAuthenticationConverter(new JwtServerAuthenticationConverter());
    jwtAuthFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/api/**"));
    return jwtAuthFilter;
  }
}
