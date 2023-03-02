package com.example.board.service;

import org.springframework.stereotype.Service;

import com.example.board.dto.GetTestResponseDto;
import com.example.board.dto.PostTestRequestDTO;
import com.example.board.dto.ResponseDto;

//! Service
//? 실제 비즈니스 로직을 담당하는 Layer
//? 일반적인 연산작업
//? Contoller로 사용자의 입력을 받아오면 해당 기능을 진행하기 위해
//? Repository에서 데이터를 가져와 작업 진행

@Service
public class MainService {

    public ResponseDto<String> getMain(){
        ResponseDto<String> result = ResponseDto.setSuccess("success", "Hello World!");
        return result;
    }

    public String getVariable(String data){
        return "GetVariable Page '" + data + "'";
    }

    public String postMain(){
        return "Post Page Response";
    }

    public String postRequestBody(String data) {
        return "Post Body Data '" + data + "'";
    }

    public String patchMain(){
        return "Patch 메서드는 수정 작업을 지정한 메서드입니다."
				+ "클라이언트로부터 데이터를 받을 땐 request body로 받습니다.";
    }

    public String deleteMain(){
        return "Delete 메서드는 삭제 작업을 지정한 메서드입니다. "
                + "클라이언트로부터 데이터를 받을 땐 pathVariable로 받습니다.";
    }

    public String postTest(PostTestRequestDTO dto) {
        return dto.toString();
    }

    public GetTestResponseDto getTest() {
        return new GetTestResponseDto(10, "Nemnem");
    }
}
