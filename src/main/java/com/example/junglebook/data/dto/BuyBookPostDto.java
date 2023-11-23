package com.example.junglebook.data.dto;

import com.example.junglebook.data.category.Category;
import com.example.junglebook.data.entity.BuyBookPost;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class BuyBookPostDto {

    //책 제목(= 게시물 제목)
    @NotNull(message = "이름은 필수 항목입니다")
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
    private String content;

    //거래 방법(직거래 or 택배 배송)
    private Boolean payment;

    //거래 완료 여부
    private Boolean completion;

    public BuyBookPost toEntity(){
        return BuyBookPost.builder()
                .bookName(bookName)
                .category(category)
                .author(author)
                .publisher(publisher)
                .field(field)
                .field2(field2)
                .price(price)
                .content(content)
                .payment(payment)
                .completion(completion)
                .build();
    }
}
