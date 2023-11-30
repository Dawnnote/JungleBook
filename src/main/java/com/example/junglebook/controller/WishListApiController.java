package com.example.junglebook.controller;

import com.example.junglebook.data.dto.WishListDto;
import com.example.junglebook.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/wishlist")
@Controller
public class WishListApiController {
    private final WishListService wishListService;

    @PostMapping("/post")
    public int save(@RequestBody WishListDto wishListDto){
        return wishListService.save(wishListDto);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int wishListId){
        wishListService.delete(wishListId);
        return wishListId;
    }

}
