package com.example.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTestRequestDTO {
	
	@NotBlank

	// 1. 문자열인 Text
	private String text;
	// 2. 정수형태로 되어 있는 number
	private int number;
	// 3. 논리형태로 되어 있는 flag
	private boolean flag;
		
}
