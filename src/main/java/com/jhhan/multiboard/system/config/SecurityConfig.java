package com.jhhan.multiboard.system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor//생성자 주입
@Configuration
@EnableWebSecurity//스프링스큐리티
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소로 접근하면 권한 및 인증을 미리 체크하기 위해 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final CustomUserDetailsService customUserDetailsService;

    //private AuthenticationFailureHandler customFailureHandler;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();//비밀번호 암호화 객체
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .roles(("USER"));
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //static 관련 인증 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring().antMatchers( "/css/**", "/js/**", "/img/**", "/bootstrap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//잠시 비활성화

        http.authorizeHttpRequests()
                .antMatchers("/", "/login", "/user/**").permitAll()
                .anyRequest().authenticated();//위에 외에는 인증 필요

        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")//낚아챌 내 로그인 페이지 호출 주소
            //.failureHandler(customFailureHandler)
            .defaultSuccessUrl("/");

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/");
    }
}
