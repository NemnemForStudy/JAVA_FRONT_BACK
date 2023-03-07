package com.example.board.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.constant.ApiMappingPattern;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.dept.PostDeptRequestDto;
import com.example.board.dto.response.dept.PostDeptResponseDto;

@RestController
@RequestMapping(ApiMappingPattern.DEPT)

public class DeptController {
    
    private static final String POST_DEPT = "/"; 

    @PostMapping(POST_DEPT)
    public ResponseDto<PostDeptResponseDto> postDept(@Valid @RequestBody PostDeptRequestDto requestBody){
        return null;
    }
}
