package com.example.junglebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyBookMainController {
    @GetMapping("/booklist")
    public String root(){
        return "redirect:/book/list";
    }
}
