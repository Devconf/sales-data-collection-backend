package com.bootcampbackend.user.controller;

import com.bootcampbackend.user.application.UserService;
import com.bootcampbackend.user.application.dto.request.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/signup")
  public ResponseEntity createUser(@Valid @RequestBody UserDTO dto) {
    userService.createUser(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
