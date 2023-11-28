package com.example.junglebook.repository;

import com.example.junglebook.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //거래후기 관련해서 필요 (UserService)
    Optional<User> findUserByNickname(String author);
}
