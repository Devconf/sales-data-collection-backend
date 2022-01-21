package com.bootcampbackend.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  User save(User toSave);

  void delete(User toRemove);

  Optional<User> findUserById(Long userId);

  List<User> findAll();
}