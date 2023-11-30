package com.example.junglebook.controller;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.data.entity.WishList;
import com.example.junglebook.service.BuyBookPostService;
import com.example.junglebook.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/wishlist")
@RequiredArgsConstructor
@Controller
public class wishListPagingController {

    private final WishListService wishListService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")
                       int page, @RequestParam(value = "kw", required = false) String kw){
        Page<WishList> paging = wishListService.getList(page);
        model.addAttribute("paging", paging);
        System.out.println("paging controller - list");
        return "wish_list";
    }

}
