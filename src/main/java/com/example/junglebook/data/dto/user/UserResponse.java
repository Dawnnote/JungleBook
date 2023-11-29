package com.example.junglebook.data.dto.user;

import com.example.junglebook.data.common.BaseTimeEntity;
import com.example.junglebook.data.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class UserResponse extends BaseTimeEntity {

    //pk, username(=email), nickname, password
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String file;
    private String filepath;
    private String phone;
    private String address;

    public UserResponse(User entity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.file = file;
        this.filepath = filepath;
        this.phone = phone;
        this.address = address;
    }

}
