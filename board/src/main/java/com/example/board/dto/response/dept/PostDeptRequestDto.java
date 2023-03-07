package com.example.board.dto.response.dept;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDeptRequestDto {

    @NotBlank
    @Length(min = 0, max = 5)
    private String deptCode;

    @NotBlank
    @Length(min = 0, max = 50)
    private String name;

    @Min(1)
    private int cheif;

    @NotBlank
    @Length(min = 0, max = 15)
    private String telNumber;
}
