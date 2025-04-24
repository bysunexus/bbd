package in.fireye.bbd.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/assets/**", "/webjars/**", "/login","/oauth2/**","/oauth2/token").permitAll()
          .anyRequest().authenticated()
      )
      .csrf(AbstractHttpConfigurer::disable)
      .formLogin(formLogin -> formLogin.loginPage("/login"));
    return http.build();
  }
}
