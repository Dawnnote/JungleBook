package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.Review;

public class ReviewResponse {
    private int reviewId;

    //SellBookId랑 합치면 주석 해제
//    private SellBookId sellBookId;

    //User랑 합치면 주석 해제
//    private User userId;
    private Boolean likeDislike;
    private String message;

    public ReviewResponse(Review entity){
        this.reviewId = entity.getReviewId();
//        this.sellBookId = entity.getSellBookId;
//        this.userId = entity.getUserId();
        this.likeDislike = entity.getLikeDislike();
        this.message = entity.getMessage();
    }
}
