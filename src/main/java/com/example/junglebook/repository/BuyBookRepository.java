package com.example.junglebook.repository;

import com.example.junglebook.data.entity.BuyBookPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyBookRepository extends JpaRepository<BuyBookPost, Integer> {
}
