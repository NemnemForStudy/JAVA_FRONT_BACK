package com.example.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.constant.ApiMappingPattern;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.example.board.dto.response.humanResource.PostHumanResourceRequestDto;
import com.example.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.example.board.service.HumanResourceService;

@RestController
@RequestMapping(ApiMappingPattern.HR)
public class HumanResourceController {
    @Autowired private HumanResourceService humanResourceService;

    private static final String Post_HUMAN_RESOURCE = "/";
    private static final String GET_HUMAN_RESOURCE = "/{eployeeNumber}";

    @PostMapping(Post_HUMAN_RESOURCE)
    //? POST http://localhost:4040/apis/hr/
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestBody) {
        ResponseDto<PostHumanResourceResponseDto> response = 
            humanResourceService.postHumanResource(requestBody);

        return response;
    }

    //? 사번을 받아오려면 path로 받아와야 해서 employeeNumber를 넣어주자
    @GetMapping(GET_HUMAN_RESOURCE)
    //? @PathVariable(pathVariable 지정 이름) 어떤 이름으로 사용할지

    //? GET http://localhost:4040/apis/hr/사번
    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(@PathVariable("employeeNumber") int employeeNumber){
        ResponseDto<GetHumanResourceResponseDto> response = humanResourceService.getHumanResource(employeeNumber);

        return response;
    }
}
 