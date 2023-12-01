package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;

import com.example.junglebook.config.PasswordMismatchException;
import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.dto.user.UserRequest;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private UserResponse of(User user) {
        return this.modelMapper.map(user, UserResponse.class);
    }

    private User of(UserResponse userResponse) {
        return modelMapper.map(userResponse, User.class);
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
        if (user.isPresent()) {
            return of(user.get());
        } else {
            throw new DataNotFoundException("USER NOT FOUNDED");
        }
    }

    public UserResponse getUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserResponse(userOptional.get());
        } else {
            throw new DataNotFoundException("User not found");
        }
    }

    @Transactional
    public void update(User user) {
        User persistence = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));

        // Validate 체크 => oauth 필드에 값이 없으면 수정 가능
        if (persistence.getProvider() == null || persistence.getProvider().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);

            // 업데이트할 필드들 설정
            persistence.setPassword(encPassword);
            persistence.setUsername(user.getUsername());
            persistence.setName(user.getName());
            persistence.setNickname(user.getNickname());
            persistence.setPhone(user.getPhone());
            persistence.setAddress(user.getAddress());

        }
    }

    //delete
    @Transactional
    public void delete(User user) {
        User persistence = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패")); }

}




