package com.nemnem.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nemnem.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    public BoardEntity findByBoardNumber(int boardNumber);
    //? Desc가 있으므로 역순
    public List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();
    public List<BoardEntity> findByWriterEmailOrderByBoardWriteDatetimeDesc(String writerEmail);
    
    //? 검색은 어떤 내용에서 단어가 포함이 되어 있거나 되어 있지 않는 것이다.
    //? 찾아서 검색을 한다고 하면 findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc
    public List<BoardEntity> findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(String boardTitle, String boardContent);

}