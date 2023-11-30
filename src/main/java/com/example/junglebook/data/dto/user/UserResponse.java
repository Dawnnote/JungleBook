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
    private String name;
    private String password;
    private String nickname;
    private String filename;
    private String filepath;
    private String phone;
    private String address;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.name = entity.getName();
        this.password = entity.getPassword();
        this.nickname = entity.getNickname();
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
    }

}