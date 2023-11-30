package com.example.junglebook.data.common;

import com.example.junglebook.data.dto.user.UserRequest;
import com.example.junglebook.data.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<UserRequest, User> {


    @Override
    public User toEntity(UserRequest userRequest) {

        return null;
    }
}
