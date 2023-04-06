package com.nemnem.board.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="유저 전화번호 중복체크 Request Body")
@Data
@NoArgsConstructor
public class ValidateTelNumberDto {
    @ApiModelProperty(value="유저 전화번호", example="010-7895-2543", required=true)
    @Length(max=13)
    @NotBlank
    private String telNumber;
}
