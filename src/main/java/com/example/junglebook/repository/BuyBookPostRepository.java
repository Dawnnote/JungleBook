package com.example.junglebook.repository;

import com.example.junglebook.data.entity.BuyBookPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyBookPostRepository extends JpaRepository<BuyBookPost, Integer> {
    List<BuyBookPost> findBySubjectLike(String bookName);
    Page<BuyBookPost> findAll(Pageable pageable);
    Page<BuyBookPost> findBySubjectContaining(String kw, Pageable pageable);
}
