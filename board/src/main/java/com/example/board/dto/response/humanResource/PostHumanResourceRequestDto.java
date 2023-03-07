package com.example.board.dto.response.humanResource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.NoArgsConstructor;

//? 여기서는 AllArgsConstructor를 쓰지 않음.
@Data
@NoArgsConstructor

public class PostHumanResourceRequestDto {
    @NotBlank                   //? 문자열이 null, 빈문자열, 공백인지 검증
    @Length(min=0, max=5)       //? 지정한 문자열의 길이 내에 있는지 검증
    private String position;            //? 직급

    @NotBlank                   
    @Length(min=0, max=10)
    private String name;                //? 이름

    @Range(min=0, max=120) // 120은 그냥 지정한 거
    private int age;                    //? 나이

    @NotBlank
    @Length(min=0, max=5)
    private String gender;              //? 성별

    @NotBlank
    @Length(min=0, max=10)
    private String academicAbility;     //? 학력

    @NotBlank
    private String birth;               //? 생년월일

    @NotBlank
    private String telNumber;           //? 폰번호

    @NotBlank
    private String address;             //? 주소

    @NotBlank
    private String addressDetail;       //? 상세주소

    @NotBlank
    private String joinDate;            //? 입사일
    private String resignationDate;     //? 퇴사일

    private String dept;                //? 부서코드

    @Min(0)
    private int annualIncome;           //? 연봉

    private String note;                //? 비고
}
//? DTO를 통해서 검증 처리가 가능하다.
