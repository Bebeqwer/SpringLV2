package com.sparta.springlv2.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {

    private String username;
    private String password;

}
