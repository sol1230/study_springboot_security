package com.study.study_springboot_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
    throws Exception {
    // None using csrf protection
    httpSecurity.csrf().disable();
    // 권한에 대한 부분 : url & roles : user url & roles
    // url, roles from Dao
    httpSecurity
      .authorizeRequests()
      // .antMatchers("/").authenticated()   // 로그인 여부만 판단.
      // .antMatchers("/admin").access("hasRole('ROLE_ADMIN')") // 로그인 & 권한
      .antMatchers("/admin")
      .authenticated()
      .anyRequest()
      .permitAll(); // 설정한 URL 이외는 접근 가능(로그인 & 로그아웃).

    // 로그인
    httpSecurity
      .formLogin()
      .loginPage("/loginForm")
      .loginProcessingUrl("/login")
      .defaultSuccessUrl("/");

    return httpSecurity.build();
    // 로그아웃
  }
}
