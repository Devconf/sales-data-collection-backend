package com.bootcampbackend.user.application.common;

import com.bootcampbackend.user.domain.RoleType;
import com.bootcampbackend.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider { // Jwt Token을 생성, 인증, 권한 부여, 유효성 검사, PK 추출 등의 다양한 기능을 제공하는 클래스

  private final long TOKEN_VALID_MILLISECOND = 1000L * 60 * 3; // 3분 나중에 바꿀예정

  @Value("spring.jwt.secret")
  private String secretKey;

  private final UserAuthenticationService userAuthenticationService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // Jwt 토큰 생성
  public String createToken(String userPk, RoleType roles) {
    Claims claims = Jwts.claims().setSubject(userPk);
    claims.put("roles", roles);
    Date now = new Date();
    return Jwts.builder()
        .setClaims(claims) // 데이터
        .setIssuedAt(now) // 토큰 발행 일자
        .setExpiration(new Date(now.getTime() + TOKEN_VALID_MILLISECOND)) // 만료 기간
        .signWith(SignatureAlgorithm.HS512, secretKey) // 암호화 알고리즘, secret 값
        .compact(); // Token 생성
  }

  // 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
  public Authentication getAuthentication(String token) {
    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

    User userDetails = userAuthenticationService.loadUserByUsername(this.getUserPk(token));
    // user의 role을 확인하여 GrantedAuthority에 권한을 넣어준다.
    if (userDetails.getRole().equals(RoleType.ADMIN)) {
      roles.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // ROLE prefix를 붙여줘야 한다.
    }
    if (userDetails.getRole().equals(RoleType.USER)) {
      roles.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    return new UsernamePasswordAuthenticationToken(userDetails, "", roles);
  }

  // Jwt Token에서 User PK 추출
  public String getUserPk(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    return req.getHeader("X-AUTH-TOKEN");
  }

  // Jwt Token의 유효성 및 만료 기간 검사
  public boolean validateToken(String jwtToken) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }
}
