package com.example.junglebook.data.entity;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class BuyBookPost extends BaseTimeEntity {
    //삽니다 상품이미지
    private String filename;
    private String filepath;

    //삽니다 상품ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyBookId;

    //게시물 작성자 (User)
//    private User userId;

    //책 제목(= 게시물 제목)
    @Column(length = 30)
    private String bookName;

    //책 카테고리
    private Category category;

    //저자
    private String author;

    //출판사
    private String publisher;

    //판매 지역(도시)
    private String field;

    //판매 지역(읍, 면, 동)
    private String field2;

    //상품 가격
    private Long price;

    //상품 설명
    @Column(columnDefinition = "TEXT", length = 255)
    private String content;

    //거래 방법(직거래 or 택배 배송)
    private Boolean payment;

    //거래 완료 여부
    private Boolean completion;

}
