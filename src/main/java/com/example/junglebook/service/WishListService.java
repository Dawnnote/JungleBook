package com.example.junglebook.service;

import com.example.junglebook.config.DataNotFoundException;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.SellBookPostResponse;
import com.example.junglebook.data.dto.WishListDto;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.WishList;
import com.example.junglebook.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class WishListService {
   private final WishListRepository wishListRepository;

   //찜목록 저장
    @Transactional
    public int save(WishListDto wishListDto){
        return wishListRepository.save(wishListDto.toEntity()).getWishListId();
    }
    //찜목록 삭제
    @Transactional
    public void delete(int id){
        WishList wishList = wishListRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 상품이 존재하지 않습니다"));
        wishListRepository.delete(wishList);
    }
    //찜목록 불러오기
    public Page<WishList> getList(int page){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders));
        System.out.println("post service - list");
        return this.wishListRepository.findAll(pageable); //검색어 비어 있으면 전체 출력


        }

    }

//    public List<SellBookPostResponse> getwishlist(int id){
//        return wishListRepository.findWishListSellBook(id).stream()
//                .map(SellBookPostResponse::new)
//                .collect(Collectors.toList());
//    }

