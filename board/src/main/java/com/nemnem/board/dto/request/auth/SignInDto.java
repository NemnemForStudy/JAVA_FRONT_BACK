package com.nemnem.board.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="로그인 RequestBody")
public class SignInDto {

    @ApiModelProperty(value="사용자 이메일", example="Nemnem@naver.com", required=true)
    @NotBlank
    @Email
    @Length(max=40)
    private String email;

    @ApiModelProperty(value="사용자 비밀번호", example="123456789123", required=true)
    @NotBlank
    @Length(min=8, max=20)
    private String password;
}