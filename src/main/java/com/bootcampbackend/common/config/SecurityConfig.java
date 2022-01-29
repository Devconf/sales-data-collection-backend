package com.bootcampbackend.common.config;

import com.bootcampbackend.common.filter.JwtAuthenticationFilter;
import com.bootcampbackend.user.application.common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable() // security에서 기본으로 생성하는 login페이지 사용 안 함
        .csrf()
        .ignoringAntMatchers("/h2-console/**")
        .disable() // csrf 사용 안 함 == REST API 사용하기 때문에
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT인증사용하므로 세션 사용  함
        .and()
        .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
        .antMatchers("/login", "/signup", "/h2-console/**")
        .permitAll() // 가입 및 인증 주소는 누구나 접근가능
        .antMatchers("/sales")
        .hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
  }

  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/login", "/signup", "/h2-console/**");
  }
}
