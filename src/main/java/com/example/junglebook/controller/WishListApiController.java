package com.example.junglebook.controller;

import com.example.junglebook.data.dto.WishListDto;
import com.example.junglebook.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WishListApiController {
    private final WishListService wishListService;

    @PostMapping("/api/wishlist")
    public int save(@RequestBody WishListDto wishListDto){
        return wishListService.save(wishListDto);
    }

    @DeleteMapping("/api/wishlist")
    public int delete(@PathVariable int wishListId){
        wishListService.delete(wishListId);
        return wishListId;
    }

}
