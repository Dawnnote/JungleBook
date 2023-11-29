package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.example.junglebook.JunglebookApplication.passwordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final ModelMapper modelMapper;

private UserResponse of (User user) {
    return this.modelMapper.map(user, UserResponse.class);
}
private User of(UserResponse userResponse) {
    return modelMapper.map(userResponse, User.class);
}
//create
    public UserResponse create(String username, String name, String nickname, String password) throws IOException {
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
    }

    //UPDATE
    @Transactional
public UserResponse update(UserResponse userResponse, String nickname, String password, String phone, String address, MultipartFile newFile) throws Exception{
    userResponse.setNickname(nickname);
    userResponse.setPassword(passwordEncoder.encode(password));
    userResponse.setPhone(phone);
    userResponse.setAddress(address);
    //새로운 파일이 전송되었을 경우에만 파일을 업로드하고 저장
    if(newFile !=null && !newFile.isEmpty()) {
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\user";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+newFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        newFile.transferTo(saveFile);
        userResponse.setFile(fileName);
        userResponse.setFilepath("/user/"+fileName);
    }

    userResponse.setModifiedDate(LocalDateTime.now());
        User user = of(userResponse);
        this.userRepository.save(user);
    return userResponse;
}
    //DELETE
    public void delete(UserResponse userResponse) {
    this.userRepository.deleteById(userResponse.getId());
    }


}
