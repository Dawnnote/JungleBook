package com.example.junglebook.controller;

import com.example.junglebook.data.entity.BuyBookPost;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/junglebook")
public class BuyBookPostController {
    private final BuyBookPostService buyBookPostService;

    @GetMapping
    public List<BuyBookPost> getAllBook(){
        return buyBookPostService.getAllBook();
    }

    }
