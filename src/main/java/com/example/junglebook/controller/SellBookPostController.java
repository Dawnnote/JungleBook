package com.example.junglebook.controller;

import com.example.junglebook.data.dto.*;
import com.example.junglebook.service.SellBookPostService;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/sell_post")
@RequiredArgsConstructor
@Controller
public class SellBookPostController {
    private final SellBookPostService sellBookPostService;
    private final SellBookPostResponse sellBookPostResponse;
    private final UserService userService;
    //게시물 화면 불러오기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        SellBookPostResponse post = this.sellBookPostService.getPost(id);
        model.addAttribute("post",post);
        return "sell_post_detail";
    }
    //게시물 등록 화면 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String post(SellBookPostRequest sellBookPostRequest){
        return "sell_post_from";
    }
    //등록화면
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid SellBookPostRequest sellBookPostRequest, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "sell_post_from";
        }
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "등록 권한이 없습니다");
        }
        UserResponse userResponse = this.userService.getUser(principal.getName());
        this.sellBookPostService.create(sellBookPostRequest.getBookName(), sellBookPostRequest.getCategory(),
                sellBookPostRequest.getBookAuthor(), sellBookPostRequest.getPublisher(), sellBookPostRequest.getField(),
                sellBookPostRequest.getField2(), sellBookPostRequest.getPrice(), sellBookPostRequest.getContent(),
                sellBookPostRequest.getPayment(), sellBookPostRequest.getCompletion(), userResponse);
        return "redirect:/sell_post/list";
    }
    //수정화면 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("update/{id}")
    public String postUpdate(SellBookPostRequest sellBookPostRequest, @PathVariable("id") Integer id, Principal principal) {
        SellBookPostResponse sellBookPostResponse = this.sellBookPostService.getPost(id);
        if(!sellBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        sellBookPostRequest.setBookName(sellBookPostResponse.getBookName());
        sellBookPostRequest.setCategory(sellBookPostResponse.getCategory());
        sellBookPostRequest.setBookAuthor(sellBookPostResponse.getBookAuthor());
        sellBookPostRequest.setPublisher(sellBookPostResponse.getPublisher());
        sellBookPostRequest.setField(sellBookPostResponse.getField());
        sellBookPostRequest.setField2(sellBookPostResponse.getField2());
        sellBookPostRequest.setPrice(sellBookPostResponse.getPrice());
        sellBookPostRequest.setContent(sellBookPostResponse.getContent());
        sellBookPostRequest.setPayment(sellBookPostResponse.getPayment());
        sellBookPostRequest.setCompletion(sellBookPostResponse.getCompletion());

        return "buy_post_update";
    }
    //수정화면
    @PreAuthorize("isAuthenticated()")
    @PostMapping("update/{id}")
    public String postUpdate(@Valid SellBookPostRequest sellBookPostRequest, BindingResult bindingResult, Principal principal,
                             @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "post_update";
        }
        SellBookPostResponse sellBookPostResponse = this.sellBookPostService.getPost(id);
        if (!sellBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.sellBookPostService.update(sellBookPostResponse, sellBookPostRequest.getBookName(), sellBookPostRequest.getCategory(),
                sellBookPostRequest.getBookAuthor(), sellBookPostRequest.getPublisher(), sellBookPostRequest.getField(),
                sellBookPostRequest.getField2(), sellBookPostRequest.getPrice(), sellBookPostRequest.getContent(),
                sellBookPostRequest.getPayment(), sellBookPostRequest.getCompletion());
        return String.format("redirect:/buy_post/detail/%s", id);   //  /buy_post/detail/{id}로 리디렉션
    }
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postdelete(Principal principal, @PathVariable("id") Integer id) {
        SellBookPostResponse sellBookPostResponse = this.sellBookPostService.getPost(id);
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제권한이 없습니다..");
        }
        if (!sellBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.sellBookPostService.delete(sellBookPostResponse);
        return "redirect:/buy_post/list";
    }

}
