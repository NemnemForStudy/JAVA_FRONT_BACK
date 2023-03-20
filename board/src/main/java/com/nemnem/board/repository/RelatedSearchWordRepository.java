package com.nemnem.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nemnem.board.entity.RelatedSearchWordEntity;
import com.nemnem.board.entity.resultSet.RelatedSearchWordResultSet;

@Repository
public interface RelatedSearchWordRepository extends JpaRepository<RelatedSearchWordEntity, Integer> {
    
    //? 일반 쿼리를 적용시키겠다.
    @Query(value="SELECT previous_search_word AS previousSearchWord, count(previous_search_word) AS count FROM Relatedsearchword WHERE search_word = ?1 GROUP BY previous_search_word ORDER BY count DESC LIMIT 15", nativeQuery=true)
    public List<RelatedSearchWordResultSet> findTop15(String searchWord);
}
