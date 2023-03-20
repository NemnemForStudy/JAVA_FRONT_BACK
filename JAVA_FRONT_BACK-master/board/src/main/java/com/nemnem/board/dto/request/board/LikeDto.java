package com.nemnem.board.dto.request.board;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDto {
    //? 필수로 받아야한다.
    @Min(1) 
    private int boardNumber; 
}
