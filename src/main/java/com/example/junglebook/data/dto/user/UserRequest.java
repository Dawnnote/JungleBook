package com.example.junglebook.data.dto.user;


import com.example.junglebook.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Integer id;
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String username;
    @NotEmpty(message = "사용자 별명은 필수 항목입니다.")
    private String nickname;
    // 기존 비밀번호
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;
    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    private String name;
//    private String filename;
//    private String filepath;
    private String phone;
    private String address;



}