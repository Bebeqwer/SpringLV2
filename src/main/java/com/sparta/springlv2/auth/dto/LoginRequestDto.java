package com.sparta.springlv2.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter // --> 이것도 불변성을 유지하게끔
@Getter
public class LoginRequestDto {

    private String username;
    private String password;

}
