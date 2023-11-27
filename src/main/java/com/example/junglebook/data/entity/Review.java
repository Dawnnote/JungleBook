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
@Builder
public class Review extends BaseTimeEntity {
    //거래후기 Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    //게시물 작성자 닉네임
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User nickName;

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

    //뭐 때문에 필요한지 알아봐야함!!
//    public void addUser(User user){
//        this.user = user;
//        this.user.getReviews().add(this);
//    }

}
