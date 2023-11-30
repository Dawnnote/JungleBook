package com.example.junglebook.controller;

import com.example.junglebook.data.entity.User;
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

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userInfoService.show();
        model.addAttribute("users", users);
        return "admin/list";
    }


    // id 값이 제대로 안넘어감
    @DeleteMapping("/list/delete")
    public String deleteMovie(@RequestParam("userId") Integer userId) {
        userInfoService.delete(userId);
        return "redirect:/user/admin/list";
    }
}
