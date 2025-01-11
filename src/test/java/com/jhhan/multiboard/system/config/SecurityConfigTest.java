package com.jhhan.multiboard.system.config;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfigTest {

    @Test
    void 비밀번호_암호화() {
        String password = "123456";
        String encodingPassword = new BCryptPasswordEncoder().encode(password);
        System.out.println("비밀번호_암호화 : " + encodingPassword);

    }
}
