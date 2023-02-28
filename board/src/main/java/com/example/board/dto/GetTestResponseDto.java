package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTestResponseDto {
	
	// 필수값이라는 제약조건을 걸어줄 수 있다.
	@NonNull
	// 1. 정수 형태의 number
	int number;
	// 2. 문자열 형태의 text
	String text;
	
}
