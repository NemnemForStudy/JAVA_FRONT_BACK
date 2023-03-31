package com.nemnem.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nemnem.board.entity.LikyEntity;
import com.nemnem.board.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPk> {
    
    public List<LikyEntity> findByBoardNumber(int boardNumber);
    public LikyEntity findByUserEmailAndBoardNumber(String userEmail, int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);

}
