package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.example.board.repository.DeptRepository;
import com.example.board.repository.EmployeeRepository;

@Service
public class HumanResourceService {
    //? Repository를 여기에 써서 사용해야함.
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private DeptRepository deptRepository;

    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto) {
        
        String telNumber = dto.getTelNumber();

        try {
            boolean hasTelNumber = employeeRepository.existByTelNumber(telNumber);
            if (hasTelNumber) return ResponseDto.setFail("Existed Telephone Number");
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail("Database Error");
        }
        
        PostHumanResourceResponseDto data = new PostHumanResourceResponseDto();
        return ResponseDto.setSuccess("Success", data);
    }
}
