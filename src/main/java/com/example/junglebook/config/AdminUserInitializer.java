package com.example.junglebook.config;

import com.example.junglebook.data.common.UserRole;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //check create
        if(!userRepository.existsByUsername("admin")) {
            //계정 생성
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setUserRole(UserRole.ADMIN);

            userRepository.save(adminUser);
        }

    }
}
