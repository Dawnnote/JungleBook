package com.example.junglebook.entity;

import com.example.junglebook.common.BaseTimeEntity;
import com.example.junglebook.common.Category;
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

    @ManyToOne
    private SalesProduct salesProduct; //판매상품
    @ManyToOne
    private User user1; //유저1
    @ManyToOne
    private User user2; //유저2

    @Enumerated(EnumType.STRING)
    private Category category;//카테고리

    private String nickname;






}
