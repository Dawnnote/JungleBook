package com.example.junglebook.repository;

import com.example.junglebook.data.entity.Review;
import com.example.junglebook.data.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    User findUserByName(String name);
    Optional<User> findUserByNickname(String author);
}
