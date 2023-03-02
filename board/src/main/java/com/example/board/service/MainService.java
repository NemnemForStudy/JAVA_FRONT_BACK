package com.example.board.service;

import org.springframework.stereotype.Service;

//! Service
//? 실제 비즈니스 로직을 담당하는 Layer
//? 일반적인 연산작업
//? Contoller로 사용자의 입력을 받아오면 해당 기능을 진행하기 위해
//? Repository에서 데이터를 가져와 작업 진행

@Service
public class MainService {

    public String getMain(){
        return "Hello World";
    }
}
