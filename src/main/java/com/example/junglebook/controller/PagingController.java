package com.example.junglebook.controller;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.service.BuyBookPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PagingController {

    private final BuyBookPostService buyBookPostService;

    //삽니다 페이징
    @GetMapping("/buy_post/list")
    public String buy_list(Model model, @RequestParam(value = "page", defaultValue = "0")
    int page, @RequestParam(value = "kw", required = false) String kw){
        Page<BuyBookPost> paging = buyBookPostService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "buy_book_list";
    }
    //팝니다 페이징
    @GetMapping("/sell_post/list")
    public String sell_list(Model model, @RequestParam(value = "page", defaultValue = "0")
    int page, @RequestParam(value = "kw", required = false) String kw){
        Page<BuyBookPost> paging = buyBookPostService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "sell_book_list";
    }
    //상품전체 페이징
    @GetMapping("/book/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")
    int page, @RequestParam(value = "kw", required = false) String kw){
        Page<BuyBookPost> paging = buyBookPostService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "book_list";
    }














}


