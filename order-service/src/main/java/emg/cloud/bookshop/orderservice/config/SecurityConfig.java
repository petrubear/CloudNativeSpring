package emg.cloud.bookshop.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
            .authorizeExchange(exchange ->
                exchange.anyExchange().authenticated()
            )
            .oauth2ResourceServer(
                ServerHttpSecurity.OAuth2ResourceServerSpec::jwt
            )
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .build();
    }
}
