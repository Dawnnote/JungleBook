package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyBookPostRequest {
    //회원이 입력하는 항목들
    @NotEmpty(message = "상품명은 필수 항목입니다")
    @Size(max=30)
    private String bookName;
    private Category category;
    private String author;
    private String publisher;
    private String field;
    private String field2;
    @NotEmpty(message = "가격은 필수 항목입니다")
    private Long price;
    private String content;
    private Boolean payment;
    private Boolean completion;

    public BuyBookPostRequest(BuyBookPost entity){
        this.bookName = entity.getBookName();
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
