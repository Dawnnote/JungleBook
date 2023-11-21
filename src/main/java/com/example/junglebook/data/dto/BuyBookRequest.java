package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyBookRequest {
    //삽니다 상품이미지
    private String filename;
    private String filepath;

    //책 제목(게시물 제목)
    @NotEmpty(message = "상품명은 필수 항목입니다")
    @Size(max = 30)
    private String bookName;

    //책 카테고리
    private Category category;

    //저자
    private String author;

    //출판사
    private String publisher;

    //거래 지역(도시)
    private String field;

    //거래 지역(읍, 면, 동)
    private String field2;

    //상품 가격
    @NotEmpty(message = "상품 가격은 필수 항목입니다")
    private Long price;

    //상품 설명
    @NotEmpty(message = "상품 설명을 입력해 주세요")
    private String content;

    //거래 방법(직거래 or 택배 배송)
    private Boolean payment;

    //거래 완료 여부
    private Boolean completion;

    public BuyBookRequest(BuyBookPost entity){
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
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
