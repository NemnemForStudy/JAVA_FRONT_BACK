package com.nemnem.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.nemnem.board.entity.BoardEntity;
import com.nemnem.board.entity.CommentEntity;
import com.nemnem.board.entity.LikyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardResponseDto {
    //? 게시물 작성하고 난 그 결과물이 필요로 하는지 확인
    //? 작성하면 화면이 뜨게 만들어 줄것임.
    
    private BoardEntity board;
    private List<CommentEntity> commentList;
    private List<LikyEntity> likeList;

    public PostBoardResponseDto(BoardEntity board) {
        this.board = board;
        this.commentList = new ArrayList<>();
        this.likeList = new ArrayList<>();
    }
}
