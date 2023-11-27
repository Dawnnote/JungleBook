package com.example.junglebook.data.dto;


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

    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String username;
    @NotEmpty(message = "사용자 별명은 필수 항목입니다.")
    private String nickname;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;
    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    private String name;



}
