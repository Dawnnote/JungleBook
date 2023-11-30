package com.example.junglebook.repository;

import com.example.junglebook.data.entity.SellBookPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellBookPostRepository extends JpaRepository<SellBookPost,Integer> {
    Page<SellBookPost> findAll(Pageable pageable);
    Page<SellBookPost> findByBookNameContaining(String kw,Pageable pageable);
}