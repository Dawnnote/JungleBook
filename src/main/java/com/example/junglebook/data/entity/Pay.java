package com.example.junglebook.data.entity;

import com.example.junglebook.data.common.BaseTimeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Pay extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //주문번호

//    @ManyToOne
//    private User user;//유저
//
//    private SalesProduct salesProduct;//상품

    private String payment;//결제방법

    private int price;//가격
}
