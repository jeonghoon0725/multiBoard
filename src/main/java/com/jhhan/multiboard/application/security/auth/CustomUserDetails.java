package com.jhhan.multiboard.application.security.auth;

import com.jhhan.multiboard.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 스프링 시큐리티가 로그인 요청을 가로채 로그인을 진행하고 완료 되면 UserDetails 타입의 오브젝트를
 * 스프링 시큐리티의 고유한 세션저장소에 저장 해준다.
 */
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;//service에서 받고

    //유저 권한 목록 조회
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //authorities.add(() -> "ROLE_" + user.getRole());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정 만료 여부 false : 만료됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부 false : 잠김
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료 여부 false : 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화 여부 false : 비활성화
    @Override
    public boolean isEnabled() {
        return true;
    }
}
