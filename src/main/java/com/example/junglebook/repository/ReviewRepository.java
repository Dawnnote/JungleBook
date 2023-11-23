package com.example.junglebook.repository;

import com.example.junglebook.data.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @EntityGraph(attributePaths = {"user"})
    List<Review> findAllByUserNickName(User nickName);
}
