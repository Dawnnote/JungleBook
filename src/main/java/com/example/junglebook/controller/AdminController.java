package com.example.junglebook.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/user/admin")
public class AdminController {
    @GetMapping("")
    public String admin() {
        return "admin/admin";
    }
}
