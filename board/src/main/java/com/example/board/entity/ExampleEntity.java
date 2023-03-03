package com.example.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//? Builder? - 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Example")
@Table(name="Example")

public class ExampleEntity {
    @Id
    //? strategy는 Auto_increment로 되는것이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prime;
    private String comment;
    private int number;
}
