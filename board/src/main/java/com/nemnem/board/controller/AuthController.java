package com.nemnem.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemnem.board.common.constant.ApiPattern;
import com.nemnem.board.dto.request.auth.SignInDto;
import com.nemnem.board.dto.request.auth.SignUpDto;
import com.nemnem.board.dto.response.ResponseDto;
import com.nemnem.board.dto.response.auth.SignInResponseDto;
import com.nemnem.board.dto.response.auth.SignUpResponseDto;
import com.nemnem.board.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(ApiPattern.AUTH)
public class AuthController {
    //? 인증과 관련된 녀석들을 처리하는 API를 작성할것이다.
    @Autowired private AuthService authService;

    //? URL은 카멜케이스 보다는 이렇게 쓰는 게 낫다.
    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    //? Auth와 관련된건 거의 POST
    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
}