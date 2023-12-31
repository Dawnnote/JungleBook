package com.example.junglebook.data.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicInsert
public class WishList {

    //위시리스트 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishListId", nullable = false)
    private int wishListId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User id;

    @ManyToOne(targetEntity = SellBookPost.class, fetch = FetchType.LAZY)
    @JoinColumn(name="sellBookId")
    private SellBookPost sellBookId;

    @Builder
    public WishList(User id, SellBookPost sellBookId){
        this.id=id;
        this.sellBookId=sellBookId;
    }
//
//    //회원 ID
//    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
//    @JoinColumn(name="id")
//    private User id;
//
//    //팝니다 게시물ID (나중에 변수명 확인하고 통일)
//    @ManyToOne(targetEntity = SellBook.class, fetch = FetchType.LAZY)
//    @JoinColumn(name="sellbookid")
//    private SellBook sellbookid;
//
//    //나중에 팝니다 Entity 합치고 변수명 확인
//    @Builder
//    public WishList(User user, SellBook sellbook){
//        this.id = id;
//        this.sellbookid = sellbookid;
//    }
}
