package com.example.rado.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterConfig;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringBootWebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/modify/{userId}").permitAll()
                .antMatchers(HttpMethod.POST, "/board").permitAll()
                .antMatchers(HttpMethod.POST, "/board/comment").permitAll()
                .antMatchers(HttpMethod.GET, "/**").permitAll()

                .anyRequest().authenticated()

                .and().build();
    }
}
