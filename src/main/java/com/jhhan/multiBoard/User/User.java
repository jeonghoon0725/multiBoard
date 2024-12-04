package com.jhhan.multiBoard.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id는 자동 생성
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String userid;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    //@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String role;

    @Column(name = "cdate", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "mdate", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public String getRoleValue() {
        return this.role;
    }
}
