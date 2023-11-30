package com.example.junglebook.repository;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BuyBookPostRepository extends JpaRepository<BuyBookPost, Integer> {

    //Paging(한 페이지에 게시물 몇개 보여줄지)
    Page<BuyBookPost> findAll(Pageable pageable);
    //키워드 검색
    Page<BuyBookPost> findByBookNameContaining(String kw, Pageable pageable);

}
