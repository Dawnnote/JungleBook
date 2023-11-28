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
    //거래 후기 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    //좋아요 싫어요
    private Boolean like1;

    //후기 내용
    private String content;

    //후기 생성 시간
    private LocalDateTime createdDate;

    //후기 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Review (int reviewId, Boolean like1, String content){
        this.reviewId = reviewId;
        this.like1 = like1;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public void addUser(User user){
        this.user = user;
        this.user.getReviews().add(this);
    }
}
