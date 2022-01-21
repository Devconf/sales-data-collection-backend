package com.bootcampbackend.user.application;

import com.bootcampbackend.user.application.common.UserMapper;
import com.bootcampbackend.user.application.dto.request.UserDTO;
import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public void createUser(UserDTO dto) {
    User user = userMapper.toUser(dto);
    userRepository.save(user);
  }
}
