package com.nemnem.board.dto.response.auth;

import com.nemnem.board.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="로그인 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
//? 쿠키에 로그인 데이터(Token) 보관, 스토어에 유저 데이터 보관
//? password 빼고 반환해주자
public class SignInResponseDto {
    //? 필수로 반환해주는 것은 required 필수
    @ApiModelProperty(value="사용자 이메일", example="Nemnem@naver.com", required=true)
    private String email;
    @ApiModelProperty(value="사용자 닉네임", example="Nemnem", required=true)
    private String nickname;
    @ApiModelProperty(value="사용자 전화번호", example="010-1234-5678", required=true)
    private String telNumber;
    @ApiModelProperty(value="사용자 주소", example="사랑시 고백구 행복동", required=true)
    private String address;
    @ApiModelProperty(value="사용자 프로필 사진 URL", example="http://~", required=true)
    private String profile;
    @ApiModelProperty(value="JWT", example="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", required=true)
    private String token;
    @ApiModelProperty(value="토큰 만료 기한", example="3600000", required=true)
    private int expiredTime;

    public SignInResponseDto(UserEntity userEntity, String token) {
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.telNumber = userEntity.getTelNumber();
        this.address = userEntity.getAddress();
        this.profile = userEntity.getProfile();
        this.token = token;
        this.expiredTime = 3600000;
    }
}