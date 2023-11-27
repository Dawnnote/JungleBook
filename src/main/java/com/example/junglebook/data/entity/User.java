package com.example.junglebook.data.entity;


import com.example.junglebook.data.common.BaseTimeEntity;
import com.example.junglebook.data.common.UserRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userRole")
public class User extends BaseTimeEntity{

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
}

