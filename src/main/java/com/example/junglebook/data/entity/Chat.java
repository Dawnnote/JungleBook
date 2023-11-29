package com.example.junglebook.data.entity;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@Getter
public class Chat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //채팅방id

    //판매상품 Entity 추가되면 변수명 확인!
//    @ManyToOne
//    private SalesProduct salesProduct; //판매상품

    //User Entity 추가하고 주석 해제하기
//    @ManyToOne
//    private User user1; //유저1
//
//    @ManyToOne
//    private User user2; //유저2

    @Enumerated(EnumType.STRING)
    private Category category;//카테고리

    private String nickname;
}