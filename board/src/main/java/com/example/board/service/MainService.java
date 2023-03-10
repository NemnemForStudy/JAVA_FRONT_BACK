package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dto.GetTestResponseDto;
import com.example.board.dto.PostTestRequestDTO;
import com.example.board.dto.response.ResponseDto;
import com.example.board.entity.ExampleEntity;
import com.example.board.repository.ExampleRepository;

//? 2023-03-06 추가
//? 접속 실패 같은 예외처리는 Service에서 호출한다.
//? 그래서 예외처리는 Service에서 해줘야 한다.

//! Service
//? 실제 비즈니스 로직을 담당하는 Layer
//? 일반적인 연산작업
//? Contoller로 사용자의 입력을 받아오면 해당 기능을 진행하기 위해
//? Repository에서 데이터를 가져와 작업 진행

@Service
public class MainService {

    //? Service에서 repository를 사용하는 방법
    //? Controller와 똑같이 @Autowired를 사용해서 생성없이 사용하게 해주자
    @Autowired
    private ExampleRepository exampleRepository; 

    //? ExampleTable에 레코드를 하나 넣는 방법
    public ResponseDto<String> getMain(){

        ExampleEntity exampleEntity = ExampleEntity.builder().comment("Hello").number(10).build();
        //? save로 exampleEntity에 저장
        exampleRepository.save(exampleEntity);
        
        ResponseDto<String> result = ResponseDto.setSuccess("success", "Hello World!");
        return result;
    }

    public ResponseDto<String> getVariable(String data){

        ExampleEntity exampleEntity = exampleRepository.findByComment(data);
        String string = exampleEntity.toString();

        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<String> postMain(){
        String string = "Post Page Response";
        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<String> postRequestBody(String data) {
        String string = "Post Body Data '" + data + "'";
        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<String> patchMain(){
        String string = "Patch 메서드는 수정 작업을 지정한 메서드입니다."
                        + "클라이언트로부터 데이터를 받을 땐 request body로 받습니다.";
        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<String> deleteMain(){
        String string = "Delete 메서드는 삭제 작업을 지정한 메서드입니다. "
                        + "클라이언트로부터 데이터를 받을 땐 pathVariable로 받습니다.";
        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<String> postTest(PostTestRequestDTO dto) {
        String string = dto.toString();
        ResponseDto<String> result = ResponseDto.setSuccess("success", string);
        return result;
    }

    public ResponseDto<GetTestResponseDto> getTest() {
        GetTestResponseDto data = new GetTestResponseDto(10, "Nemnem");
        ResponseDto<GetTestResponseDto> result = ResponseDto.setSuccess("success", data);
        return result;
    }

    public void descriptionJpaMethod() {
        //# JpaRepository 기본 메서드
        //! 1. findById(PK).get();
        //? 해당 테이블에서 PK를 기준으로 값을 검색해옴
        ExampleEntity exampleEntity = exampleRepository.findById(0).get();

        //! 2. findAll();
        //? 해당 테이블의 모든 레코드를 검색한 결과를 반환
        List<ExampleEntity> entityList = exampleRepository.findAll();

        //! 3. save(entityInstance);
        //? 해당 테이블에 특정 레코드를 삽입 or 수정
        //? Primary Key 기준으로 Primary key에 해당하는 레코드가 없으면 삽입
        //? Primary Key에 해당하는 레코드가 있으면 해당 레코드 수정
        exampleRepository.save(exampleEntity);

        //! 4. existsById(PK);
        //? 해당 테이블에 PK를 기준으로 레코드가 존재하면 True,
        //? 존재하지 않으면 False 반환
        boolean hasEntity = exampleRepository.existsById(0);

        //! 5. deleteById(PK);
        //? 해당 테이블에 PK 기준으로 특정 레코드를 삭제
        exampleRepository.deleteById(0);
    }
}
