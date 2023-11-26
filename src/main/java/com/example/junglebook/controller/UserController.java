package com.example.junglebook.controller;

import com.example.junglebook.data.dto.ReviewDto;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    //거래후기 조회
    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> getReviewsByNickName(@RequestParam User nickName){
        return new ResponseEntity<>(userService.getReviewsByNickName(nickName), HttpStatus.OK);
    }
    //거래후기 추가
    @PostMapping("/review")
    public void addReview(@Valid @RequestBody ReviewDto reviewDto){
        userService.addReview(reviewDto);
    }

}
