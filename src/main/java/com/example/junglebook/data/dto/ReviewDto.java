package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.Review;
import com.example.junglebook.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class ReviewDto {

    private int reviewId;
    private User author;
    @NotEmpty(message = "좋아요/싫어요 선택은 필수 항목입니다")
    private Boolean likeDislike;
    @NotEmpty(message = "거래 후기 내용은 필수 항목입니다")
    private String message;

    public Review toEntity(ReviewDto reviewDto){
        return Review.builder()
                .likeDislike(reviewDto.getLikeDislike())
                .message(reviewDto.getMessage())
                .build();
    }

    //getAuthor 에러 안 나는지 확인 (review.getUser().getNickname())이어야 하는지
    public static ReviewDto toDto(Review review){
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .author(review.getAuthor())
                .message(review.getMessage())
                .build();
    }
}
