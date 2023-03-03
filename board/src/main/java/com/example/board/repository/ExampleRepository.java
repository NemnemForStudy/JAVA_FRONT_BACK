package com.example.board.repository;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.ExampleEntity;

//# @Repository
//? 해당 인터페이스를 Repository로 사용하도록 지정하는 어노테이션
@Repository
//? JpaRepository<T, ID>: 해당 인터페이스를 상속 받은 인터페이스를 
//? JPA Query Createion을 사용할 수 있도록하는 인터페이스
//? T: DB의 테이블을 구현한 Entity Class가 위치
//? ID: Entity Primary Key의 타입
//? findBy는 select * 과 같은 의미
public interface ExampleRepository extends JpaRepository<ExampleEntity, Integer> {

    //? Comment에 들어갈 녀석을 뒤에 ()에서 파라미터를 적어줌
    public ExampleEntity findByComment(String comment);
}
