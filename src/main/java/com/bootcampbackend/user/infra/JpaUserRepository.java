package com.bootcampbackend.user.infra;

import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {}
