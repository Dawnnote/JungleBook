package com.example.junglebook.controller;


import com.example.junglebook.data.dto.user.UserRequest;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
//@RequestMapping("/user")
public class UserController {

    //service
    private final UserService userService;

    @GetMapping("/signup")
    public String join(UserRequest userRequest) {
        return "user/joinForm";
    }

    @PostMapping("/signup")
    public String join(@Valid UserRequest userRequest, BindingResult bindingResult, MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "user/joinForm";
        }
        if (!userRequest.getPassword1().equals(userRequest.getPassword2())) {
            bindingResult.rejectValue("password2", "passIncorrect", "패스워드가 일치하지 않습니다.");
            return "user/joinForm";
        }

        try {
            userService.create(userRequest.getUsername(), userRequest.getNickname(), userRequest.getPassword1(), userRequest.getName());


        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 가입된 회원입니다.");
            return "user/joinForm";
        }
        catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/joinForm";
        }
        return "redirect:/user/login";
    }

    //login
    @GetMapping("/login")
    public String login() {
        return "user/loginForm";
    }

    //admin
    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin page";
    }

}