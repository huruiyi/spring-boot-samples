package com.example.GenericType.OOP4.Override2;

abstract class Person {

  String name;
  String surname;

  Person() {

  }
  Person(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  abstract void getDescription();
}