package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    //会创建 hibernate_sequence表，
    // create table hibernate_sequence (next_val bigint) engine=InnoDB
    // select next_val as id_val from hibernate_sequence for update
    // update hibernate_sequence set next_val= ? where next_val=?
    // @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    private String email;
}
