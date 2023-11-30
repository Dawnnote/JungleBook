package com.example.junglebook.controller;

import com.example.junglebook.config.auth.PrincipalDetails;
import com.example.junglebook.data.dto.user.UserRequest;
import com.example.junglebook.data.dto.user.UserResponse;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

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

    //user -> mypage -> update user information -> upload profile photo
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myPage/{id}")
    public String updateUser(@PathVariable(name = "id", required = false) Integer id, UserRequest userRequest, Principal principal) {
        if (id == null) {
            // id가 필수이지만 값이 없을 경우 예외 처리 또는 기본값 설정
            // 여기에서는 예외 처리를 하도록 했습니다.
            throw new IllegalArgumentException("User ID is required.");
        }

        UserResponse userResponse = this.userService.getUser(id);

        if (!userResponse.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        userRequest.setName(userResponse.getName());
        userRequest.setUsername(userResponse.getUsername());
        userRequest.setNickname(userResponse.getNickname());
        userRequest.setPassword1(userResponse.getPassword());
        userRequest.setPassword2(userResponse.getPassword());
        userRequest.setFilename(userResponse.getFilename());
        userRequest.setFilepath(userResponse.getFilepath());
        userRequest.setPhone(userResponse.getPhone());
        userRequest.setAddress(userResponse.getAddress());

        return "user/myPage";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/myPage/{id}")
    public String updateUser(@AuthenticationPrincipal PrincipalDetails principalDetails,
                             @ModelAttribute("userRequest") @Valid UserRequest userRequest,
                             BindingResult bindingResult,
                             @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "user/myPage";
        }

        // 현재 비밀번호 확인
        String currentUsername = principalDetails.getUsername();
        String currentPassword = userRequest.getCurrentPassword();
        if (!userService.isCurrentPasswordValid(currentUsername, currentPassword)) {
            bindingResult.rejectValue("currentPassword", "passwordIncorrect", "현재 비밀번호가 일치하지 않습니다.");
            return "user/myPage";
        }

        try {
            userService.updateUserInfo(currentUsername, userRequest.getCurrentPassword(), userRequest.getNickname(), userRequest.getPhone(), userRequest.getAddress(), file);
            return "redirect:/user/myPage";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception and return an appropriate view or redirect
            return "/form_errors";
        }
    }



}