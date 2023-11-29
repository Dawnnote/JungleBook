package com.example.junglebook.service;

import com.example.junglebook.data.dto.SellBookPostResponse;
import com.example.junglebook.data.dto.WishListDto;
import com.example.junglebook.data.entity.WishList;
import com.example.junglebook.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class WishListService {
   private final WishListRepository wishListRepository;

    @Transactional
    public int save(WishListDto wishListDto){
        return wishListRepository.save(wishListDto.toEntity()).getWishListId();
    }

    @Transactional
    public void delete(int id){
        WishList wishList = wishListRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 상품이 존재하지 않습니다"));
        wishListRepository.delete(wishList);
    }

    public List<SellBookPostResponse> getwishlist(int id){
        return wishListRepository.findWishListSellBook(id).stream()
                .map(SellBookPostResponse::new)
                .collect(Collectors.toList());
    }
}
