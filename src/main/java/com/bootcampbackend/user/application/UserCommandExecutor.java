package com.bootcampbackend.user.application;

import com.bootcampbackend.user.application.common.PasswordEncoder;
import com.bootcampbackend.user.application.common.UserMapper;
import com.bootcampbackend.user.application.dto.request.SignUpUserDTO;
import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandExecutor {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void createUser(SignUpUserDTO dto) {
    String salt = passwordEncoder.getSalt();
    String hashedPW = passwordEncoder.encode(dto.getPassword(), salt);

    Optional<User> findUser = userRepository.findUserByEmail(dto.getEmail());

    if (findUser.isPresent()) {
      throw new IllegalArgumentException("해당 이메일이 이미 존재합니다.");
    }

    User user =
        userMapper.toUser(
            dto.getCompanyName(),
            dto.getBusinessNum(),
            dto.getEmail(),
            hashedPW,
            dto.getRole(),
            salt);
    userRepository.save(user);
  }
}
