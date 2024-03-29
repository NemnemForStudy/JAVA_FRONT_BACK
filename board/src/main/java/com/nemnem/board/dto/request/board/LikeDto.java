package com.nemnem.board.dto.request.board;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="좋아요 기능 Request Body")
@Data
@NoArgsConstructor
public class LikeDto {
    @ApiModelProperty(value="게시물 번호", example="1", required=true)
    //? 필수로 받아야한다.
    @Min(1) 
    private int boardNumber; 
}
