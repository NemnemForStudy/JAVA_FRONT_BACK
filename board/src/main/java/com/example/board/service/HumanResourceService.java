package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.common.constant.ResponseMessage;
import com.example.board.dto.response.ResponseDto;
import com.example.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.example.board.dto.response.humanResource.PostHumanResourceRequestDto;
import com.example.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.example.board.entity.EmployeeEntity;
import com.example.board.repository.DeptRepository;
import com.example.board.repository.EmployeeRepository;

@Service
public class HumanResourceService {
    //? Repository를 여기에 써서 사용해야함.
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private DeptRepository deptRepository;

    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto) {
        
        PostHumanResourceResponseDto data = null;

        String telNumber = dto.getTelNumber();
        String departmentCode = dto.getDept();

        try {
            boolean hasTelNumber = employeeRepository.existsByTelNumber(telNumber);
            if (hasTelNumber) return ResponseDto.setFail(ResponseMessage.EXIST_TEL_NUMBER);
            
            if (departmentCode != null) {
                boolean hasDepartment = deptRepository.existsById(departmentCode);
                if (!hasDepartment) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_DEPT_CODE);
            }

            EmployeeEntity employeeEntity = new EmployeeEntity(dto);
            employeeRepository.save(employeeEntity);

            data = new PostHumanResourceResponseDto(employeeEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.ERROR_MESSAGE);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS_MESSAGE, data);
    }

    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(int employeeNumber) {

        GetHumanResourceResponseDto data = null;

        //? 존재하지 않는 사번에 대해서
        try{

            //? employeeRepository에 메소드 새로 추가
            EmployeeEntity employeeEntity = employeeRepository.findByEmployeeNumber(employeeNumber);
            if(employeeEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_EMPLOYEE_NUMBER);

            data = new GetHumanResourceResponseDto(employeeEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.ERROR_MESSAGE);
        } 

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS_MESSAGE, data);

    }

}
