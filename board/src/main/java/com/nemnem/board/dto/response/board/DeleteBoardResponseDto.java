package com.nemnem.board.dto.response.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="특정 게시물 삭제 Response body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardResponseDto {
    @ApiModelProperty(value="특정 게시물 삭제 결과", example="true", required=true)
    private boolean resultStatus;
}