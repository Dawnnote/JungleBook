package com.example.junglebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyPost")
public class BuyBookMainController {
    @GetMapping("/")
    public String root(){
        return "redirect:/buy_post/list";
    }
}
