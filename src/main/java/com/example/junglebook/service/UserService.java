package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private UserResponse of(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserResponse create(String username, String name, String nickname, String password) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserRole(UserRole.USER);
        userRepository.save(user);
        System.out.println("create service - check");
        return of(user);
    }

    //update
    //
    public UserResponse update (UserResponse userResponse, String nickname, String password, String phone, String address, MultipartFile newFile)  throws Exception {
        userResponse.setNickname(nickname);
        userResponse.setPassword(password);
        userResponse.setPhone(phone);
        userResponse.setAddress(address);

        //새로운 파일이 전송되었을 경우 파일 업로드 / 저장
        // 새로운 파일이 전송되었을 경우에만 파일을 업로드하고 저장
        if (newFile != null && !newFile.isEmpty()) {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload";
            UUID uuid = UUID.randomUUID(); // 랜덤 식별자
            String fileName = uuid + "_" + newFile.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            newFile.transferTo(saveFile);

            userResponse.setFilename(fileName);
            userResponse.setFilepath("/user/" + fileName);
        }

        userResponse.setModifiedDate(LocalDateTime.now());
        User user = of(userResponse);
        this.userRepository.save(user);
        return userResponse;
    }


    public void delete(UserResponse userResponse) {
        userRepository.deleteById(userResponse.getId());
    }



    public boolean isCurrentPasswordValid(String username, String currentPassword) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(currentPassword, user.getPassword());
    }
}