package com.bootcampbackend.user.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;

  private String businessNum;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private RoleType role;

  private String salt;

  protected User() {}

  public User(
      String companyName,
      String businessNum,
      String email,
      String password,
      RoleType role,
      String salt) {
    this.companyName = companyName;
    this.businessNum = businessNum;
    this.email = email;
    this.password = password;
    this.role = role;
    this.salt = salt;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof User)) return false;
    if (this == another) return true;

    return this.id == ((User) another).id;
  }
}
