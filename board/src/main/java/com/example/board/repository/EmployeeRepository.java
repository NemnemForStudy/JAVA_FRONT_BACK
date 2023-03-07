package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.EmployeeEntity;

@Repository

//? JpaRepository<Entity 클래스명, Primary key 데이터 타입>
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    //? existById 메서드를 생성하면 DB에 접근한 다음에 telNumber가 존재하는지 안하는지 확인 후
    //? 있으면 true, 없으면 false 출력

    public boolean existsByTelNumber(String telNumber);

    public EmployeeEntity findByEmployeeNumber(int employeeNumber);

}
