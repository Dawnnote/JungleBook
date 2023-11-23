package com.example.junglebook.controller;

import com.example.junglebook.data.dto.BuyBookPostRequest;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.service.BuyBookPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/buy_post")
@RequiredArgsConstructor
@Controller
public class BuyBookPostController {
    private final BuyBookPostService buyBookPostService;
    private final BuyBookPostResponse buyBookPostResponse;
    //UserService 합치면 주석 해제
//    private final UserService userService;

    //buyBookId 이거 빨간줄 이유 찾아야 함
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("buyBookId") Integer buyBookId) {
        BuyBookPostResponse post = this.buyBookPostService.getList(buyBookId);
        model.addAttribute("post", post);
        return "buy_post_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String postCreate(BuyBookPostRequest buyBookPostRequest){
        return "buy_post_form";
    }

    //UserService 합치면 주석 해제
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String postCreate(@Valid BuyBookPostRequest buyBookPostRequest, BindingResult bindingResult, Principal principal){
//        if (bindingResult.hasErrors()){
//            return "buy_post_form";
//        }
//        if (principal == null){
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN), "등록 권한이 없습니다");
//        }
//        UserResponse userResponse = this.userService.getUser(principal.getName());
//        this.buyBookPostService.create(buyBookPostRequest.getBookName(), buyBookPostRequest.getCategory(),
//                buyBookPostRequest.getAuthor(), buyBookPostRequest.getPublisher(), buyBookPostRequest.getField(),
//                buyBookPostRequest.getField2(), buyBookPostRequest.getPrice(), buyBookPostRequest.getContent(),
//                buyBookPostRequest.getPayment(), buyBookPostRequest.getCompletion(), userResponse);
//        return "redirect:/buy_post/list";
//    }



}
