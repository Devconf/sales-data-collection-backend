package com.bootcampbackend.user.application;

import com.bootcampbackend.user.application.common.JwtTokenProvider;
import com.bootcampbackend.user.application.common.PasswordEncoder;
import com.bootcampbackend.user.application.common.UserMapper;
import com.bootcampbackend.user.application.dto.request.LogInUserDTO;
import com.bootcampbackend.user.application.dto.response.GetAuthorizedUserDTO;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryProcessor {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public List<GetUserDTO> getAllUser() {
    return userRepository.findAll().stream()
        .map(userMapper::toUserDto)
        .collect(Collectors.toList());
  }

  public GetAuthorizedUserDTO checkAuthorizedUser(LogInUserDTO dto) {
    User user =
        userRepository
            .findUserByEmail(dto.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

    // 비밀번호 확인 등의 유효성 검사 진행
    String getSalt = user.getSalt();

    String loginPassword = passwordEncoder.encode(dto.getPassword(), getSalt);

    if (loginPassword.equals(user.getPassword())) {
      String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
      return userMapper.toAuthUserDto(
          user.getCompanyName(), user.getBusinessNum(), user.getEmail(), token);
    }
    throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
  }
}
