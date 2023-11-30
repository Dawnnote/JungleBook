package com.example.junglebook.controller;

import com.example.junglebook.data.dto.BuyBookPostResponse;
import com.example.junglebook.data.dto.ReportRequest;
import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.service.BuyBookPostService;
import com.example.junglebook.service.ReportService;
import com.example.junglebook.service.UserService;
import groovyjarjarasm.asm.tree.AbstractInsnNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RequestMapping("/report")
@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;
    private final UserService userService;
    private final BuyBookPostService buyBookPostService;

    //신고하기 작성
    @PostMapping("/create/{id}")
    public String createReport(Model model, @PathVariable("id") Integer id, @Valid ReportRequest reportRequest, BindingResult bindingResult, Principal principal){
        BuyBookPostResponse buyBookPostResponse = this.buyBookPostService.getPost(id);
        UserResponse userResponse = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()){
            model.addAttribute("post", buyBookPostResponse);
            return "buy_post_detail";
        }
        ReportResponse reportResponse = this.reportService.create(buyBookPostResponse, reportRequest.getReportType(), userResponse);
        return "redirect:/buy_post/list";
    }

}
