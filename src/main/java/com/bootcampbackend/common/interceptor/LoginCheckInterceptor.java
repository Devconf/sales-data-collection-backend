package com.bootcampbackend.common.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (HttpMethod.OPTIONS.matches(request.getMethod())) {
      return true;
    }
    HttpSession session = request.getSession();

    boolean isNotLoggedIn = session == null || session.getAttribute("") == null;

    // 미인증 사용자 요청
    if (isNotLoggedIn) {
      return true;
    }
    return true;
  }
}
