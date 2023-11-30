package com.example.junglebook.data.dto;

import com.example.junglebook.data.entity.WishList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishListDto {
    private int id;
    private int sellBookId;


    @Builder
    public WishListDto(int id, int sellBookId){
        this.id=id;
        this.sellBookId=sellBookId;
    }

    public WishList toEntity() {
        return WishList.builder()
//                .id(id)
//                .sellBookId(sellBookId)
                .build();
    }
}
