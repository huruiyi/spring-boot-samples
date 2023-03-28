package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;

/**
     create table country (
         id integer not null auto_increment,
         capital varchar(255),
         code varchar(255),
         name varchar(255),
         primary key (id)
     ) engine=InnoDB
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "capital")
    private String capital;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
