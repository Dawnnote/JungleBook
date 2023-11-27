package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review extends BaseTimeEntity {
    //거래후기 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    //거래후기 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    //좋아요 싫어요
    private Boolean likeDislike;

    //후기 내용
    @Column(length = 255, columnDefinition = "TEXT")
    private String message;

    //후기 생성 시간
    private LocalDateTime createdDate;

    @Builder
    public Review(Integer reviewId, Boolean likeDislike, String message){
        this.reviewId = reviewId;
        this.likeDislike = likeDislike;
        this.message = message;
        this.createdDate = LocalDateTime.now();
    }

    //이게 어떤 역할을 하는지 모르겠음
//    public void addUser(User user){
//        this.author = author;
//        this.author.getReviews().add(this);
//    }
}
