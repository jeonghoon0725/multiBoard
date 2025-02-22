package com.jhhan.multiboard.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
//@Table(name = "users") //h2 test할 때 user는 예약어
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id는 자동 생성
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* nickname과 password만 수정 가능 */
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
