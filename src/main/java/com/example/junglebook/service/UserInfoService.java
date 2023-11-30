package com.example.junglebook.service;

import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    public List<User> show() {
        return userRepository.findAll();
    }
    public void delete(int id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
    }

    public User index(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
