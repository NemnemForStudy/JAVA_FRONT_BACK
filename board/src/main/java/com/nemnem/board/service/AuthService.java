package com.nemnem.board.service;


import com.nemnem.board.dto.request.auth.SignInDto;
import com.nemnem.board.dto.request.auth.SignUpDto;
import com.nemnem.board.dto.response.ResponseDto;
import com.nemnem.board.dto.response.auth.SignInResponseDto;
import com.nemnem.board.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto);
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
}
