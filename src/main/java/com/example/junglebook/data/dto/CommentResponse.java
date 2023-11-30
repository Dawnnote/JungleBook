package com.example.junglebook.data.dto;

import com.example.junglebook.data.dto.user.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private int id;
    private String content;
    private BuyBookPostResponse post;
    private UserResponse author;
}
