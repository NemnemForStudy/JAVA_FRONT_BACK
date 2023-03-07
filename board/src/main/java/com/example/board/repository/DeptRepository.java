package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.DeptEntity;

@Repository
public interface DeptRepository extends JpaRepository<DeptEntity, String>{
    
}
