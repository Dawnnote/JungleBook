package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.BaseTimeEntity;
import com.example.junglebook.data.common.UserRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userRole")
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username; //email로 받기
    private String name; //사용자 이름
    private String password; //비밀번호
    private String nickname; //별명
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
//    private String provider; //
//    private String providerId;
    //enum


    //생성자
    @Builder
    public User (String username, String name, String nickname, String password, Timestamp createDate) {
        this.username = username;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
//        this.provider = provider;
//        this.providerId = providerId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}