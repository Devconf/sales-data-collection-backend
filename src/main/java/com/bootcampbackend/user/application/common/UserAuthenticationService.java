package com.bootcampbackend.user.application.common;

import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public User loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =
        userRepository
            .findUserByEmail(email)
            .orElseThrow(() -> new ArrayIndexOutOfBoundsException(email));
    return user;
  }
}
