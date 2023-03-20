package com.nemnem.board.dto.response.board;

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
public class LikeResponseDto {
    private BoardEntity board;
    //? 좋아요 횟수로 전체를 반환해줘야할거 같다.
    private List<CommentEntity> commentList;
    private List<LikyEntity> likeList;
}
