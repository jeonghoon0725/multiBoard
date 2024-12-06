package com.jhhan.multiboard.user.dto;

import com.jhhan.multiboard.user.entity.Role;
import com.jhhan.multiboard.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private Long id;

    private String username;

    private String password;

    private String email;

    private String nickname;

    private Role role;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .role(role.USER)
                .build();
        return user;
    }
}
