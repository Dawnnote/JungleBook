package com.example.junglebook.repository;

import com.example.junglebook.data.entity.Review;
import com.example.junglebook.data.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    //이 부분 잘 모르겠음
//    @EntityGraph(attributePaths = {"user"})
//    List<Review> findAllByUserNickName(String nickname);
}
