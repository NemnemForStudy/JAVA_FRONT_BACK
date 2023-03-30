package com.nemnem.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.nemnem.board.entity.BoardEntity;
import com.nemnem.board.entity.CommentEntity;
import com.nemnem.board.entity.LikyEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="게시물 작성 Response body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardResponseDto {
    //? 게시물 작성하고 난 그 결과물이 필요로 하는지 확인
    //? 작성하면 화면이 뜨게 만들어 줄것임.
    
    @ApiModelProperty(value="게시물 Entity", required=true)
    private BoardEntity board;

    @ApiModelProperty(value="댓글 Entity list", required=true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value="좋아요 Entity list", required=true)
    private List<LikyEntity> likeList;

    public PostBoardResponseDto(BoardEntity board) {
        this.board = board;
        this.commentList = new ArrayList<>();
        this.likeList = new ArrayList<>();
    }
}