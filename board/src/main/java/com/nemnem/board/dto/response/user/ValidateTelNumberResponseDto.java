package com.nemnem.board.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="전화번호 중복 체크 Request Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTelNumberResponseDto {
    @ApiModelProperty(value="중복체크 결과", example="true", required=true)
    private boolean result;
}
