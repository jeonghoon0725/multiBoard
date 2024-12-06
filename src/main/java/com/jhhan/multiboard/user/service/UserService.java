package com.jhhan.multiboard.user.service;

import com.jhhan.multiboard.user.dto.UserDto;
import com.jhhan.multiboard.user.entity.User;
import com.jhhan.multiboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public Long userJoin(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
