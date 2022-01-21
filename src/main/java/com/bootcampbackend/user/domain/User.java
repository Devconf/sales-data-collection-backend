package com.bootcampbackend.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;

  private String businessNum;

  private String email;

  private String password;

  private String passwordValid;

  protected User() {}

  public User(
      String companyName, String businessNum, String email, String password, String passwordValid) {
    this.companyName = companyName;
    this.businessNum = businessNum;
    this.email = email;
    this.password = password;
    this.passwordValid = passwordValid;
  }
}
