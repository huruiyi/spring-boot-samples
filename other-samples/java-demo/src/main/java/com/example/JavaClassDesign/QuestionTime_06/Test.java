package com.example.JavaClassDesign.QuestionTime_06;/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

class Test {

  public static void main(String args[]) {
    Test test = new Test();
    test.print(10);
  }

  public void print(Integer i) {
    System.out.println("Integer");
  }

  public void print(int i) {
    System.out.println("int");
  }

  public void print(long i) {
    System.out.println("long");
  }
}
