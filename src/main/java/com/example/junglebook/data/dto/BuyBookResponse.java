package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class BuyBookResponse {
    private String filename;
    private String filepath;
    private int buyBookId;
    //User랑 합쳐지면 주석 해제
//    private User userId;
    private String bookName;
    private Category category;
    private String author;
    private String publisher;
    private String Field;
    private String Field2;
    private Long price;
    private String content;
    private Boolean payment;
    private Boolean completion;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    public BuyBookResponse(BuyBookPost entity){
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
        this.buyBookId = entity.getBuyBookId();
        this.bookName = entity.getBookName();
        this.category = entity.getCategory();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.Field = entity.getField();
        this.Field2 = entity.getField2();
        this.price = entity.getPrice();
        this.content = entity.getContent();
        this.payment = entity.getPayment();
        this.completion = entity.getCompletion();
    }

}
