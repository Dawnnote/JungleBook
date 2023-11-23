package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.Review;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDto {
    //좋아요 싫어요
    @NotNull(message = "평가는 필수 항목입니다")
    private Boolean likeDislike;

    //후기 내용
    @NotNull(message = "내용은 필수 항목입니다")
    private String message;

    public Review toEntity(){
        return Review.builder()
                .likeDislike(likeDislike)
                .message(message)
                .build();
    }
}
