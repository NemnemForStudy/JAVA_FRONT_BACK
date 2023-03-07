package com.example.board.dto.response.dept;

import com.example.board.entity.DeptEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDeptResponseDto {
    private String deptCode;
    private String name;
    private int cheif;
    private String telNumber;

    public PostDeptResponseDto(DeptEntity deptEntity) {
        this.deptCode = deptEntity.getDeptCode();
        this.name = deptEntity.getName();
        this.cheif = deptEntity.getCheif();
        this.telNumber = deptEntity.getTelNumber();
    }
}
