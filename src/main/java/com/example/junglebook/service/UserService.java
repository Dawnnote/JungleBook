package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;

import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final ModelMapper modelMapper;

private UserResponse of (User user) {
    return this.modelMapper.map(user, UserResponse.class);
}

//create

public UserResponse create(String username, String nickname, String password, String name) throws IOException {
//public UserResponse create(String username, String name, String nickname, String password) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserRole(UserRole.USER);
        this.userRepository.save(user);
        return of(user);
    }

    //nickname select
    public UserResponse getUser(String username) {
       Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if(user.isPresent()) {
            return of(user.get());
        }
        else {
            throw new DataNotFoundException("USER NOT FOUNDED");
        }

        //UPDATE

        //DELETE
    }


}
