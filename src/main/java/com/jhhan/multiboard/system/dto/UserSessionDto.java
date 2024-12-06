package com.jhhan.multiboard.system.dto;

import com.jhhan.multiboard.user.entity.Role;
import com.jhhan.multiboard.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    /* Entity -> DTO */
    public UserSessionDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
