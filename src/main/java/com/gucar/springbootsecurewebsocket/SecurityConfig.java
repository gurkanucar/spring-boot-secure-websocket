package com.gucar.springbootsecurewebsocket;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/", "/ws/**")
        .permitAll()
        .and()
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .and()
        .logout(logout -> logout.logoutSuccessUrl("/"));
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user =
        User.withDefaultPasswordEncoder().username("admin").password("123").roles("USER").build();
    UserDetails user2 =
        User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build();
    return new InMemoryUserDetailsManager(List.of(user, user2));
  }
}
