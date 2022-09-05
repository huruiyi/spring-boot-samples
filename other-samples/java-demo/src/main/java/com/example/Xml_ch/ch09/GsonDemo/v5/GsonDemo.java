package com.example.Xml_ch.ch09.GsonDemo.v5;

import com.google.gson.Gson;

import static java.lang.System.*;

public class GsonDemo
{
   static class Person
   {
      String name;
      int age;

      Person(String name, int age)
      {
         this.name = name;
         this.age = age;
      }
   }

   public static void main(String[] args)
   {
      Person p = new Person("Jane Doe", 59);
      Gson gson = new Gson();
      String json = gson.toJson(p);
      out.println(json);
   }
}