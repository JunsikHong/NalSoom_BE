package com.pick.nalsoom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId); //아이디 중복검사
}
