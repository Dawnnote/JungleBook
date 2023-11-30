package com.example.junglebook.controller;

import com.example.junglebook.data.entity.Report;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.service.ReportService;
import com.example.junglebook.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/user/admin")
public class AdminController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userInfoService.show();
        model.addAttribute("users", users);
        return "admin/list";
    }

    // 신고 리스트
    @GetMapping("/report")
    public String reportList(Model model) {
        List<Report> reports = reportService.findAll();
        model.addAttribute("reports", reports);
        return "admin/report";
    }


    // 유저 삭제
    @DeleteMapping("/list/delete")
    public String deleteMovie(@RequestParam("userId") Integer userId) {
        userInfoService.delete(userId);
        return "redirect:/user/admin/list";
    }
}
