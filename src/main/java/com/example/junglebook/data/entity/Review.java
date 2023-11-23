package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Review extends BaseTimeEntity {
    //거래후기 Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    //상품Id (팝니다 게시물)
//    private SellbookId sellbookId;

    //게시물 작성자
//    private User userId;

    //좋아요 싫어요
    private Boolean likeDislike;

    //후기 내용
    @Column(length = 255, columnDefinition = "TEXT")
    private String message;

    public ReviewDto toDto(){
        return ReviewDto.builder()
                .likeDislike(likeDislike)
                .message(message)
                .build();
    }

}
