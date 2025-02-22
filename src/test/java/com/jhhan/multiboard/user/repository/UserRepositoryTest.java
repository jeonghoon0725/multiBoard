package com.jhhan.multiboard.user.repository;

import com.jhhan.multiboard.infrastructure.persistence.UserRepository;
import com.jhhan.multiboard.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @AfterEach
    public void clear() {
        User user = userRepository.findByNickname("테스트계정");
        //userRepository.delete(user);// 데이터 초기화
    }

    @Test
    public void 유저_생성_가져오기() {
        // Given
        String username = "test0099";
        String rawPassword = "1q2w3e4r!!";//1234
        String encPassword = encoder.encode(rawPassword);

//        userRepository.save(User.builder()
//                .username(username)
//                .password(encPassword)
//                .nickname("테스트유저")
//                .email("test0099@test.com")
//                .role(Role.USER)
//                .build());

        // When
        List<User> userList = userRepository.findAll();

        // Then
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(encoder.matches(rawPassword, user.getPassword())).isTrue(); // 비밀번호 검증
    }
}
