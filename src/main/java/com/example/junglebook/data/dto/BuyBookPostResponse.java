package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.Img;
import com.example.junglebook.data.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BuyBookPostResponse  {
    private int buyBookId;
    private User author;
    private String bookName;
    //private Category category;
    private String category;
    //글 작성자 아니고 책의 저자
    private String bookAuthor;
    private String publisher;
    private String field;
    //private String field2;
    private Long price;
    private String content;
   // private Boolean payment;
    //private Boolean completion;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private String payment;
    private String completion;

    private List<Img> img;

    public BuyBookPostResponse(BuyBookPost entity){
        this.buyBookId = entity.getBuyBookId();
        this.bookName = entity.getBookName();
        this.author = entity.getAuthor();
        this.category = entity.getCategory();
        this.bookAuthor = entity.getBookAuthor();
        this.publisher = entity.getPublisher();
        this.field = entity.getField();
       // this.field2 = entity.getField2();
        this.price = entity.getPrice();
        this.content = entity.getContent();
        this.payment = entity.getPayment();
        this.completion = entity.getCompletion();
        this.img = entity.getImg();

    }

    public void setId(User author) {
    }
}
