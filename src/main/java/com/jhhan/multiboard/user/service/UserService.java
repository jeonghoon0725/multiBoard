package com.jhhan.multiboard.user.service;

import com.jhhan.multiboard.user.dto.UserDto;
import com.jhhan.multiboard.user.dto.UserRequestDto;
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

    // 영속성 컨텍스트 - 변경 감지(더티 체킹)
    public void modifyUser(UserRequestDto dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickname(), encPassword);
    }

}
