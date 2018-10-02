package com.ruiyi.model;

public class Person implements Comparable<Person> {
    Integer Id;
    String Name;
    Integer Age;


    public Person(Integer id, String name, int age) {
        Id = id;
        Name = name;
        Age = age;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "Id=" + Id + ", Name='" + Name + ", Age=" + Age + '}';
    }

    /*
    按照姓名排序
     */
    public int compareTo(Person person) {
        return this.getAge().compareTo(person.getAge());
    }
}