package com.example.user.dto;

import com.example.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSigninResponseDto {
    private Long id;
    private String role;
    private String slackName;
}
