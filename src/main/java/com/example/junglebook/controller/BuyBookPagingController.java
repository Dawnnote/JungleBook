package com.example.junglebook.controller;

import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.service.BuyBookPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/buy_post")
@RequiredArgsConstructor
@Controller
public class BuyBookPagingController {

    private final BuyBookPostService buyBookPostService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")
                       int page, @RequestParam(value = "kw", required = false) String kw){
        Page<BuyBookPost> paging = buyBookPostService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "buy_book_list";
    }

}
