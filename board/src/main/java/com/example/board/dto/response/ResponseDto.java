package com.example.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "set")
@NoArgsConstructor
//? Generic을 사용하기 위해 Dto뒤에 <D>를 적었고 D는 임의로 정해주는 제네릭이다.
public class ResponseDto<D> {

    //? Response 결과 상태 (정상: true, 비정상: false)
    private boolean status;
    //? Response 결과 메세지
    private String message;
    //? Response 결과 데이터
    private D data;

    //? 아래 메서드에서만 쓰는 제네릭을 추가해줘야한다. ResponseDto앞에 <D>추가
    //? data는 Generic이므로 D에서 받아오기 때문에 D를 썼다.
    //# 성공시에 대한 인스턴스를 생성해주는 static 생성자
    //? <D> Response<D>
    //? 먼저오는 <D>는 해당 메서드에서 독립적으로 사용할 Generic을 지칭
    //? 뒤에오는 <D>는 ResponseDto 클래스 타입에 필요로 하는 Generic을 지칭
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        //? message, data는 외부에서 받아온다.
        return ResponseDto.set(true, message, data);
    }

    //# 실패시에 대한 인스턴스를 생성해주는 static 생성자
    public static <D> ResponseDto<D> setFail(String message) {
        
        return ResponseDto.set(false, message, null);
    }
}