package com.nemnem.board.service;

import com.nemnem.board.dto.request.user.PatchProfileDto;
import com.nemnem.board.dto.response.ResponseDto;
import com.nemnem.board.dto.response.user.PatchProfileResponseDto;

public interface UserService {
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto);
}
