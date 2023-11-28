package com.example.junglebook.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BuyBookPost buyBookPost; //게시글번호

    private String fileName; //uuid와 합쳐진 이름
    private String filePath;
    private String originName; //original name

    @Builder
    public Img(Long id, BuyBookPost buyBookPost, String fileName, String filePath, String originName){
        this.id = id;
        this.buyBookPost = buyBookPost;
        this. fileName = fileName;
        this.filePath = filePath;
        this.originName = originName;
    }
}
