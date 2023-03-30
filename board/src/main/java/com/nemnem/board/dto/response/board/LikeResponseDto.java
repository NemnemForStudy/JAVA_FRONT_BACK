package com.nemnem.board.dto.response.board;

import java.util.List;

import com.nemnem.board.entity.BoardEntity;
import com.nemnem.board.entity.CommentEntity;
import com.nemnem.board.entity.LikyEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="좋아요 기능 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {
    @ApiModelProperty(value="게시물 Entity", required=true)
    private BoardEntity board;

    @ApiModelProperty(value="댓글 Entity list", required=true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value="좋아요 Entity list", required=true)
    private List<LikyEntity> likeList;
}