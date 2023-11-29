package com.example.junglebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

//1. 코드 받기 2. 액세스 토큰 받기(권한)
//3. 사용자 프로필 정보 가져오기
// 4-1. 그 정보를 토대로 회원가입을 자동으로 진행 시키기도 함
// 4-2. 그러나 필요한 정보가 부족할 경우 추가적으로 집 주소가 필요함
// 이 경우 추가적인 회원가입 창이 나와서 회원가입을 따로 완료해야 함

@Configuration
@EnableWebSecurity //Security 활성화 -> 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
//prePostEnabled = true -> preAuthorize, postAuthorize 활성화
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured annotaion 활성화
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //csrf.disable
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/buy_post/**").authenticated() //인증 필요
                .antMatchers("/user/joinForm").anonymous() //회원가입 페이지는 인증되지 않은 사람도 접근 가능
                .antMatchers("/**").permitAll() //인증, 비인증 관계 없이 모두 접근 가능
                .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .and()        //여기에 OAuth2 설정 추가
                .csrf().disable();


        return http.build();
    }

    //AccessDeniedHandler
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.sendRedirect("/error/forbidden");
        };
    }
}

