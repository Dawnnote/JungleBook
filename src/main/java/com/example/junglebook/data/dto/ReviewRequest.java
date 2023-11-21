package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.Review;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    private Boolean likeDislike;
    @NotEmpty(message = "거래 후기를 남겨주세요")
    private String message;

    public ReviewRequest(Review entity){
        this.likeDislike = entity.getLikeDislike();
        this.message = entity.getMessage();
    }
}
