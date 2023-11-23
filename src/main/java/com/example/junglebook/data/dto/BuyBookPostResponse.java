package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class BuyBookPostResponse {
    private int buyBookId;
    //User Entity 합치면 주석 해제
//    private User userId;
//    private User nickName;
    private String bookName;
    private Category category;
    //글 작성자 아니고 책의 저자
    private String author;
    private String publisher;
    private String field;
    private String field2;
    private Long price;
    private String content;
    private Boolean payment;
    private Boolean completion;
    private LocalDateTime modifiedDate;
    private LocalDateTime createDate;

    public BuyBookPostResponse(BuyBookPost entity){
        this.buyBookId = entity.getBuyBookId();
        this.bookName = entity.getBookName();
//        this.nickName = entity.getNickName();
        this.category = entity.getCategory();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.field = entity.getField();
        this.field2 = entity.getField2();
        this.price = entity.getPrice();
        this.content = entity.getContent();
        this.payment = entity.getPayment();
        this.completion = entity.getCompletion();

    }
}
