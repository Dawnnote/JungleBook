package com.example.junglebook.controller;

import com.example.junglebook.data.dto.ReportRequest;
import com.example.junglebook.data.dto.ReportResponse;
import com.example.junglebook.data.dto.UserResponse;
import com.example.junglebook.data.entity.User;
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

    //신고하기 화면 불러오기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        ReportResponse report = this.reportService.getPost(id);
        model.addAttribute("post", post);
        return "report_post_detail";
    }

    //신고하기 등록 화면 불러오기
    @GetMapping("/create")
    public String post(ReportRequest reportRequest){
        return "report_post_form";
    }

    //신고하기 등록 화면
    @PostMapping("/create")
    public String postCreate(@Valid ReportRequest reportRequest, BindingResult bindingResult, Principal principal) throws IOException{
        if (bindingResult.hasErrors()){
            return "report_post_form";
        }

        UserResponse userResponse = this.userService.getUser(principal.getName());
        this.reportService.create(reportRequest.getReportedId(), reportRequest.getReportType(), userResponse);
        return "redirect:/sell_post/detail";
    }
}
