package com.bootcampbackend.user.controller;

import com.bootcampbackend.user.application.UserCommandExecutor;
import com.bootcampbackend.user.application.UserQueryProcessor;
import com.bootcampbackend.user.application.dto.request.LogInUserDTO;
import com.bootcampbackend.user.application.dto.request.SignUpUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserCommandExecutor userCommandExecutor;
  private final UserQueryProcessor userQueryProcessor;

  @PostMapping(value = "/signup")
  public ResponseEntity createUser(@Valid @RequestBody SignUpUserDTO dto) {
    userCommandExecutor.createUser(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/login")
  public ResponseEntity loginUser(@Valid @RequestBody LogInUserDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(userQueryProcessor.checkAuthorisedUser(dto));
  }
}
