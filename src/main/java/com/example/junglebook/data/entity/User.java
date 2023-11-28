package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Component
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String password;
    private Date userBirth;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String userAddress;
    private String userAddressDetail;
    //    deviceToken (String) - 푸시알림
    //private String deviceToken;
//refreshTokenValue(String) - 세션유지
    //private String refreshTokenValue;




    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Review> reviews = new LinkedHashSet<>();

    @Builder
    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
