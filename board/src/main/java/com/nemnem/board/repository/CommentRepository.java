package com.nemnem.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nemnem.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    //? 댓글은 시간이 있어야 한다.(정렬)
    public List<CommentEntity> findByBoardNumberOrderByWriteDatetimeDesc(int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);

}
