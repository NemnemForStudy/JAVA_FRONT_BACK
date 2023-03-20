package com.nemnem.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//? staticName은 자신이 원하는 거 해도 무방
@AllArgsConstructor(staticName="set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    //? D를 정의해주기 위해 ResponseDto(반환타입)앞에 D를 붙여준다. 
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }
    
}