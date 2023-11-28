package com.example.junglebook.controller;

import com.example.junglebook.data.dto.BuyBookPostRequest;
import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.BuyBookPost;
import com.example.junglebook.service.BuyBookPostService;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/buy_post")
@RequiredArgsConstructor
@Controller
public class BuyBookPostController {
    private final BuyBookPostService buyBookPostService;
    private final BuyBookPostResponse buyBookPostResponse;
    private final UserService userService;

    //삽니다 게시물 화면 불러오기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        BuyBookPostResponse post = this.buyBookPostService.getPost(id);

        model.addAttribute("post", post);
        return "buy_post_detail2";
    }

    //삽니다 게시물 등록 화면 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String post(BuyBookPostRequest buyBookPostRequest){
        return "buy_post_form2";
    }

    //삽니다 게시물 등록 화면
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid BuyBookPostRequest buyBookPostRequest, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "buy_post_form";
        }
        if (principal == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "등록 권한이 없습니다");
        }
        //나중에 userService랑 합치고 getUser 다시 확인
        UserResponse userResponse = this.userService.getUser(principal.getName());
        this.buyBookPostService.create(buyBookPostRequest.getBookName(), buyBookPostRequest.getCategory(),
                buyBookPostRequest.getBookAuthor(), buyBookPostRequest.getPublisher(), buyBookPostRequest.getField(),
                 buyBookPostRequest.getPrice(), buyBookPostRequest.getContent(),
                buyBookPostRequest.getPayment(), buyBookPostRequest.getCompletion(), userResponse);
        System.out.println("post controller - postcreate redirect list");
        return "redirect:/buy_post/list";
    }

    //삽니다 게시물 수정 화면 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("update/{id}")
    public String postUpdate(BuyBookPostRequest buyBookPostRequest, @PathVariable("id") Integer id, Principal principal) {
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        if(!buyBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        buyBookPostRequest.setBookName(buyBookPostResponse.getBookName());
        buyBookPostRequest.setCategory(buyBookPostResponse.getCategory());
        buyBookPostRequest.setBookAuthor(buyBookPostResponse.getBookAuthor());
        buyBookPostRequest.setPublisher(buyBookPostResponse.getPublisher());
        buyBookPostRequest.setField(buyBookPostResponse.getField());
        //buyBookPostRequest.setField2(buyBookPostResponse.getField2());
        buyBookPostRequest.setPrice(buyBookPostResponse.getPrice());
        buyBookPostRequest.setContent(buyBookPostResponse.getContent());
        buyBookPostRequest.setPayment(buyBookPostResponse.getPayment());
        buyBookPostRequest.setCompletion(buyBookPostResponse.getCompletion());

        return "buy_post_update";
    }

    //삽니다 게시물 수정 화면
    @PreAuthorize("isAuthenticated()")
    @PostMapping("update/{id}")
    public String postUpdate(@Valid BuyBookPostRequest buyBookPostRequest, BindingResult bindingResult, Principal principal,
                             @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "post_update";
        }
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        if (!buyBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.buyBookPostService.update(buyBookPostResponse, buyBookPostRequest.getBookName(), buyBookPostRequest.getCategory(),
                buyBookPostRequest.getBookAuthor(), buyBookPostRequest.getPublisher(), buyBookPostRequest.getField(),
                 buyBookPostRequest.getPrice(), buyBookPostRequest.getContent(),
                buyBookPostRequest.getPayment(), buyBookPostRequest.getCompletion());
        return String.format("redirect:/buy_post/detail/%s", id);   //  /buy_post/detail/{id}로 리디렉션
    }

    //삽니다 게시물 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postdelete(Principal principal, @PathVariable("id") Integer id) {
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제권한이 없습니다..");
        }
        if (!buyBookPostResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.buyBookPostService.delete(buyBookPostResponse);
        return "redirect:/buy_post/list";
    }






}
