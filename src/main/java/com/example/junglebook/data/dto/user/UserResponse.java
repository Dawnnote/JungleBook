package com.example.junglebook.data.dto.user;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.common.BaseTimeEntity;

import com.example.junglebook.data.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Data
@NoArgsConstructor
public class UserResponse extends BaseTimeEntity {

    //pk, username(=email), nickname, password
    private Integer id;
    private String username;
    private String nickname;
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
//    private String password2;
    private String confirmWord;
    private String name;
    private String filename;
    private String filepath;
    private String phone;
    private String address;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.nickname = entity.getNickname();
        this.password1 = entity.getPassword();
        //this.password2 = entity.getPassword();
        this.confirmWord = entity.getPassword();
        this.name = entity.getName();
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
    }


}