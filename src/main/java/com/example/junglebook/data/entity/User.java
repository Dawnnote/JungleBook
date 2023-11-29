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
public class User extends BaseTimeEntity {

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
    //create
    @CreationTimestamp
    private Timestamp createDate;
    ////OAuth2.0 login
    private String provider;
    private String providerId;
    //file
    private String filename;
    private String filepath;
    //phone
    private String phone;
    //address
    private String address;




    //생성자
    @Builder
    public User (String username, String name, String password, String nickname, UserRole userRole, Timestamp createDate, String provider, String providerId, String filename, String filepath, String phone, String address) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.userRole = userRole;
        this.createDate = createDate;
        this.provider = provider;
        this.providerId = providerId;
        this.filename = filename;
        this.filepath = filepath;
        this.phone = phone;
        this.address = address;
    }
}