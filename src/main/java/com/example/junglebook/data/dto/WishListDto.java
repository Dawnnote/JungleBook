package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.SellBook;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.data.entity.WishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishListDto {
//    //User id, 판매상품 sellbookid로 안해도 되는지??
//    private Long id;
//    private Long sellbookId;
//
//    @Builder
//    public WishListDto(Long id, Long sellbookId){
//        this.id = id;
//        this.sellbookId = sellbookId;
//    }
//
//    public WishListDto(SellBook sellBook) {
//    }
//
//    public WishList toEntity(){
//        return WishList.builder()
////                .id(id)
////                .sellbookId(sellbookId)
//                .build();
//    }
}
