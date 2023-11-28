package com.example.junglebook.data.dto;

import com.example.junglebook.data.common.BaseTimeEntity;
import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
public class UserResponse extends BaseTimeEntity {

    private Integer id;
    private String nickname;

    public UserResponse(User entity){
        this.id = entity.getId();
        this.nickname = entity.getNickname();
    }
}
