package com.nemnem.board.dto.response;

import com.nemnem.board.common.constant.ResponseMessage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="Response Format")
@Data
@NoArgsConstructor
//? staticName은 자신이 원하는 거 해도 무방
@AllArgsConstructor(staticName="set")
public class ResponseDto<D> {

    @ApiModelProperty(value="작업결과 상태", example="true", required=true)
    private boolean result;
    @ApiModelProperty(value="작업결과 Message", example=ResponseMessage.SUCCESS, required=true)
    private String message;
    @ApiModelProperty(value="작업결과 Data", required=true)
    private D data;

    //? D를 정의해주기 위해 ResponseDto(반환타입)앞에 D를 붙여준다. 
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }
    
}