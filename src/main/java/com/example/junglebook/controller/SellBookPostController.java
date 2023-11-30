package com.example.junglebook.controller;

import com.example.junglebook.data.dto.*;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.service.BuyBookPostService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequestMapping("/sell_post")
@RequiredArgsConstructor
@Controller
public class SellBookPostController {
//    private final SellBookPostService sellBookPostService;
//    private final SellBookPostResponse sellBookPostRespons
    private final BuyBookPostService buyBookPostService;

    private final UserService userService;

    //게시물 등록 화면 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String post(BuyBookPostRequest buyBookPostRequest){
        return "sell_post_form";
    }
    //등록화면

    //팝니다 게시물 화면 불러오기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentRequest commentRequest) {
        BuyBookPostResponse post = this.buyBookPostService.getPostReadCnt(id);

        model.addAttribute("post", post);
        return "sell_post_detail";
    }

    //팝니다 게시물 등록  version2
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid BuyBookPostRequest buyBookPostRequest, BindingResult bindingResult, Principal principal, List<MultipartFile> files) throws IOException {
        if (bindingResult.hasErrors()){
            return "sell_post_form";
        }
        if (principal == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "등록 권한이 없습니다");
        }
        //나중에 userService랑 합치고 getUser 다시 확인
        UserResponse userResponse = this.userService.getUser(principal.getName());
        this.buyBookPostService.create(buyBookPostRequest.getBookName(), buyBookPostRequest.getCategory(),
                buyBookPostRequest.getBookAuthor(), buyBookPostRequest.getPublisher(), buyBookPostRequest.getField(),
                buyBookPostRequest.getPrice(), buyBookPostRequest.getContent(),
                buyBookPostRequest.getPayment(), buyBookPostRequest.getCompletion(), userResponse, files, buyBookPostRequest.getPurpose());

        return "redirect:/sell_post/list";
    }

    //삭제 version2
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postdelete(Principal principal, @PathVariable("id") Integer id) {
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);

        this.buyBookPostService.delete(buyBookPostResponse);
        return "redirect:/sell_post/list";
    }

    //팝니다 게시물 수정 화면 불러오기 version2
    @PreAuthorize("isAuthenticated()")
    @GetMapping("update/{id}")
    public String postUpdate(BuyBookPostRequest buyBookPostRequest, @PathVariable("id") Integer id, Principal principal) {
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        if(!buyBookPostResponse.getAuthor().getUsername().equals(principal.getName())) {
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
        buyBookPostRequest.setImg(buyBookPostResponse.getImg());
        buyBookPostRequest.setId(buyBookPostResponse.getBuyBookId());

        return "sell_post_update";
    }
    //팝니다 게시물 수정 화면 version2
    @PreAuthorize("isAuthenticated()")
    @PostMapping("update/{id}")
    public String postUpdate(@Valid BuyBookPostRequest buyBookPostRequest, BindingResult bindingResult, Principal principal,
                             @PathVariable("id") Integer id, List<MultipartFile> files) throws IOException {
        if (bindingResult.hasErrors()) {
            return "post_update";
        }
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        if (!buyBookPostResponse.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.buyBookPostService.update(buyBookPostResponse, buyBookPostRequest.getBookName(), buyBookPostRequest.getCategory(),
                buyBookPostRequest.getBookAuthor(), buyBookPostRequest.getPublisher(), buyBookPostRequest.getField(),
                buyBookPostRequest.getPrice(), buyBookPostRequest.getContent(),
                buyBookPostRequest.getPayment(), buyBookPostRequest.getCompletion(), files);
        return String.format("redirect:/sell_post/detail/%s", id);   //  /buy_post/detail/{id}로 리디렉션
    }









}
