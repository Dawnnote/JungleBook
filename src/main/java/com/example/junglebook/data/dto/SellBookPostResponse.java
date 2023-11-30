package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.Comment;
import com.example.junglebook.data.entity.SellBookPost;
import com.example.junglebook.data.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class SellBookPostResponse {
    private int SellBookId;
    private User author;
    private String bookName;
    private Category category;
    private String bookAuthor;
    private String publisher;
    private String field;
    private String field2;
    private Long price;
    private String content;
    private Boolean payment;
    private Boolean completion;
    private LocalDateTime modifiedDate;
    private LocalDateTime createDate;

    public SellBookPostResponse(SellBookPost entity){
        this.SellBookId=entity.getSellBookId();
        this.bookName = entity.getBookName();
        this.category = entity.getCategory();
        this.bookAuthor = entity.getBookAuthor();
        this.publisher = entity.getPublisher();
        this.field = entity.getField();
        this.field2 = entity.getField2();
        this.price = entity.getPrice();
        this.content = entity.getContent();
        this.payment = entity.getPayment();
        this.completion = entity.getCompletion();
    }
    public void setId(User author){
    }

}