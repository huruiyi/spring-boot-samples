package com.example.Lambda.Listing_06;/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.util.Random;
import java.util.stream.Stream;

class GenerateBooleans {

  public static void main(String[] args) {
    Random random = new Random();
    Stream.generate(random::nextBoolean).limit(2).forEach(System.out::println);
  }
}
