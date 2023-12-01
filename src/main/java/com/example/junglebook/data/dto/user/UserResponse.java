package com.example.junglebook.data.dto.user;

import com.example.junglebook.data.common.BaseTimeEntity;

import com.example.junglebook.data.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserResponse extends BaseTimeEntity {

    private Integer id;
    private String username;
    private String nickname;
    private String name;
    private String phone;
    private String address;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.nickname = entity.getNickname();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
    }
}
