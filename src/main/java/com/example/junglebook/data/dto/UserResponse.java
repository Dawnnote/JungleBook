package com.example.junglebook.data.dto;

import com.example.junglebook.data.common.BaseTimeEntity;

import com.example.junglebook.data.entity.User;
import lombok.Data;

@Data
public class UserResponse extends BaseTimeEntity {

    //pk, username(=email), nickname, password
    private int id;
    private String username;
    private String password;
    private String nickname;


}
