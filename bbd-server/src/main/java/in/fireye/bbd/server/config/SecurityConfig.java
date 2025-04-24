package in.fireye.bbd.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfig {


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,SecurityProperties securityProperties) throws Exception {
    http
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers(securityProperties.getAnonymousPaths()).permitAll()
          .anyRequest().authenticated()
      )
      .csrf(AbstractHttpConfigurer::disable)
      .formLogin(formLogin -> formLogin.loginPage("/login"));
    return http.build();
  }
}
