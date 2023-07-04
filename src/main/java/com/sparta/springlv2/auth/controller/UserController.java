package com.sparta.springlv2.auth.controller;

import com.sparta.springlv2.auth.dto.SignupRequestDto;
import com.sparta.springlv2.auth.service.UserService;
import com.sparta.springlv2.posting.dto.Message;
import com.sparta.springlv2.posting.dto.StatusEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    @ResponseBody
    public ResponseEntity<Message> signup(@Valid @RequestBody SignupRequestDto requestDto,
                                          BindingResult bindingResult,
                                          HttpServletResponse res){
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        message.setStatus(StatusEnum.OK);
        message.setMessage("회원가입 성공");
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("회원가입 실패");
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
        userService.signup(requestDto);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}