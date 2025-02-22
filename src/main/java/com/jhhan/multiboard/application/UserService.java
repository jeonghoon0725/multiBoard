package com.jhhan.multiboard.application;

import com.jhhan.multiboard.application.dto.UserRequestDto;
import com.jhhan.multiboard.domain.User;
import com.jhhan.multiboard.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public Long userJoin(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(encoder.encode(userRequestDto.getPassword()));

        return userRepository.save(userRequestDto.toEntity()).getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 영속성 컨텍스트 - 변경 감지(더티 체킹)
    public void modifyUser(UserRequestDto dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(()
                -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickname(), encPassword);
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
