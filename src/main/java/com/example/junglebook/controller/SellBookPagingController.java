package com.example.junglebook.controller;

import com.example.junglebook.data.entity.SellBookPost;
import com.example.junglebook.service.SellBookPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sell_post")
@RequiredArgsConstructor
@Controller
public class SellBookPagingController {
    private final SellBookPostService sellBookPostService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")
    int page, @RequestParam(value = "kw", required = false) String kw){
        Page<SellBookPost> paging = sellBookPostService.getPage(kw, page);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "buy_book_list";
    }
}
