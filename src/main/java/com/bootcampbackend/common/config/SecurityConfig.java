package com.bootcampbackend.common.config;

import com.bootcampbackend.common.filter.JwtAuthenticationFilter;
import com.bootcampbackend.user.application.common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable() // security에서 기본으로 생성하는 login페이지 사용 안 함
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .csrf()
        .ignoringAntMatchers("/h2-console/**")
        .disable() // csrf 사용 안 함 == REST API 사용하기 때문에
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT인증사용하므로 세션 사용  함
        .and()
        .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
        .antMatchers("/login", "/signup", "/h2-console/**")
        .permitAll() // 가입 및 인증 주소는 누구나 접근가능
        .antMatchers("/sales/upload")
        // hasRole에서 ROLE prefix를 붙여주기때문에 jwtTokenProvider에서 new
        // SimpleGrantedAuthority("ROLE_USER")로 권한을 넣어줘야한다.
        .hasAnyRole("USER", "ADMIN") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
        .antMatchers("/sales/download/**")
        .hasAnyRole("USER", "ADMIN")
        .antMatchers("/sales/**")
        .hasRole("ADMIN")
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOriginPattern("http://localhost:3000");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/login", "/signup", "/h2-console/**");
  }
}
