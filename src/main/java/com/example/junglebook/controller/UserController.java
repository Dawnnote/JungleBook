package com.example.junglebook.controller;


import com.example.junglebook.data.dto.user.UserRequest;
import com.example.junglebook.data.entity.User;
import com.example.junglebook.repository.UserRepository;
import com.example.junglebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
@Validated
public class UserController {

    //service
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String join(UserRequest userRequest) {
        return "user/joinForm";
    }

    @PostMapping("/signup")
    public String join(@Valid UserRequest userRequest, BindingResult bindingResult) {
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
        } catch (Exception e) {
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userInfo")
    public String update(Model model, Principal principal) {
        // 현재 사용자 정보를 가져와서 모델에 추가
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("userRequest", user);
        return "user/updateUser";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userRequest") @Valid User userRequest, BindingResult bindingResult, Model model, Principal principal) {
        // Validation 체크
        if (bindingResult.hasErrors()) {
            return "user/updateUser"; // 유효성 검사 실패 시 다시 폼으로 이동
        }

        // 현재 사용자 정보를 가져와서 업데이트
        String username = principal.getName();
        User existingUser = userRepository.findByUsername(username);

        // 업데이트할 필드 설정
        existingUser.setName(userRequest.getName());
        existingUser.setNickname(userRequest.getNickname());
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword())); // 비밀번호 업데이트 시 암호화 필요
        existingUser.setPhone(userRequest.getPhone());
        existingUser.setAddress(userRequest.getAddress());

        // userRepository를 통해 업데이트
        userRepository.save(existingUser);

        // 업데이트 후 리다이렉트 또는 다른 처리
        return "redirect:/";  // 수정이 완료된 후 리다이렉트할 경로
    }

//delete
// UserController.java
@DeleteMapping("/delete/{userId}")
public String deleteUser(@PathVariable Long userId) {
    // userRepository.deleteById(userId);
    return "User with ID " + userId + " has been deleted.";
}

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/delete/{id}")// Delete user
//    public String userDelete(Principal principal, @PathVariable("id") Integer id) {
//        UserResponse userResponse = this.userService.getUser(id);
//
//        if (principal == null) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
//        }
//
//        if (!userResponse.getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
//        }
//
//        this.userService.delete(userResponse);
//        return "redirect:/";
//    }


}

