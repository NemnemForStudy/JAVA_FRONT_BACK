package com.nemnem.board.service;

import com.nemnem.board.dto.request.user.PatchProfileDto;
import com.nemnem.board.dto.request.user.ValidateEmailDto;
import com.nemnem.board.dto.request.user.ValidateNicknameDto;
import com.nemnem.board.dto.request.user.ValidateTelNumberDto;
import com.nemnem.board.dto.response.ResponseDto;
import com.nemnem.board.dto.response.user.GetUserResponseDto;
import com.nemnem.board.dto.response.user.PatchProfileResponseDto;
import com.nemnem.board.dto.response.user.ValidateEmailResponseDto;
import com.nemnem.board.dto.response.user.ValidateNicknameResponseDto;
import com.nemnem.board.dto.response.user.ValidateTelNumberResponseDto;

public interface UserService {
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto);
    public ResponseDto<GetUserResponseDto> getUser(String email);
    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto);
    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto dto);
    public ResponseDto<ValidateTelNumberResponseDto> validateTelnumber(ValidateTelNumberDto dto);
}
