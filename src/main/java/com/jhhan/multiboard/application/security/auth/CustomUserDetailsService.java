package com.jhhan.multiboard.application.security.auth;

import com.jhhan.multiboard.application.dto.UserSessionDto;
import com.jhhan.multiboard.domain.User;
import com.jhhan.multiboard.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    private final HttpSession session;

    /**
     * username DB에 있는지 체크
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new UserSessionDto(user));

        //시큐리티 세션에 유저 정보 저장
        return new CustomUserDetails(user);
    }
}
