package com.bootcampbackend.user.application.common;

public interface PasswordEncoder {
  public String encode(String password, String salt);

  public String getSalt();
}
