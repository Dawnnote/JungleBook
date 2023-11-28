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
    private String nickname;
    @NotEmpty(message = "좋아요/싫어요 선택은 필수 항목입니다")
    private Boolean like;
    @NotEmpty(message = "거래 후기 내용은 필수 항목입니다")
    private String content;

    public Review toEntity(ReviewDto reviewDto){
        return Review.builder()
                .like(reviewDto.getLike())
                .content(reviewDto.getContent())
                .build();
    }

    public static ReviewDto toDto(Review review){
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .nickname(review.getUser().getNickname())
                .content(review.getContent())
                .build();
    }
}
