package com.jhhan.multiboard.user.repository;

import com.jhhan.multiboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //security & null 처리
    Optional<User> findByUsername(String username);

    //
    User findByNickname(String nickname);
}
