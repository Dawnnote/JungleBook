package com.example.junglebook.controller;

import com.example.junglebook.data.dto.*;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.service.BuyBookPostService;
import com.example.junglebook.service.CommentService;
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

import javax.swing.*;
import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final BuyBookPostService buyBookPostService;
    private final CommentService commentService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment (Model model, @PathVariable("id") Integer id,
                                 @Valid CommentRequest commentRequest, BindingResult bindingResult, Principal principal){
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        UserResponse userResponse = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("post", buyBookPostResponse);
            return "buy_post_detail2";
        }
        CommentResponse commentResponse = this.commentService.create(buyBookPostResponse, commentRequest.getContent(), userResponse);
        return String.format("redirect:/buy_post/detail/%s#comment_%s",
                commentResponse.getPost().getBuyBookId(), commentResponse.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create1/{id}")
    public String createCommentSell(Model model, @PathVariable("id") Integer id,
                                    @Valid CommentRequest commentRequest, BindingResult bindingResult, Principal principal){
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        UserResponse userResponse = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("post", buyBookPostResponse);
            return "sell_post_detail";
        }
        CommentResponse commentResponse = this.commentService.create(buyBookPostResponse, commentRequest.getContent(), userResponse);
        return String.format("redirect:/sell_post/detail/%s#comment_%s",
                commentResponse.getPost().getBuyBookId(), commentResponse.getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("update/{id}")
    public String commentUpdate(CommentRequest commentRequest, @PathVariable("id") Integer id, Principal principal) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        commentRequest.setContent(commentResponse.getContent());
        return "comment_update";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("update1/{id}")
    public String commentUpdateSell(CommentRequest commentRequest, @PathVariable("id") Integer id, Principal principal){
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        commentRequest.setContent(commentResponse.getContent());
        return "comment_update";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("update/{id}")
    public String commentUpdate(@Valid CommentRequest commentRequest, BindingResult bindingResult, Principal principal,
                                @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "comment_update";
        }
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.commentService.update(commentResponse, commentRequest.getContent());
        return String.format("redirect:/buy_post/detail/%s#comment_%s",
                commentResponse.getPost().getBuyBookId(), commentResponse.getId());
        //getAuthor 확인
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("update1/{id}")
    public String commentUpdateSell(@Valid CommentRequest commentRequest, BindingResult bindingResult, Principal principal,
                                    @PathVariable("id") Integer id){
        if(bindingResult.hasErrors()){
            return "comment_update";
        }
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())){
        }
        this.commentService.update(commentResponse, commentRequest.getContent());
        return String.format("redirect:/sell_post/detail/%s#comment_%s",
                commentResponse.getPost().getBuyBookId(), commentResponse.getId());

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String commentDelete(Principal principal, @PathVariable("id") Integer id) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제권한이 없습니다..");
        }
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.commentService.delete(commentResponse);
        return String.format("redirect:/buy_post/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete1/{id}")
    public String commentDeleteSell(Principal principal, @PathVariable("id") Integer id) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제권한이 없습니다..");
        }
        if (!commentResponse.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.commentService.delete(commentResponse);
        return String.format("redirect:/sell_post/detail/%s", id);
    }


}
