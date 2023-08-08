package com.example.GenericsAndCollections.Listing_19;/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

class StreamPipelineComponents {

  public static void main(String[] args) {
    Method[] objectMethods = Object.class.getMethods();
    Stream<Method> objectMethodStream = Arrays.stream(objectMethods);
    Stream<String> objectMethodNames = objectMethodStream.map(method -> method.getName());
    Stream<String> uniqueObjectMethodNames = objectMethodNames.distinct();
    uniqueObjectMethodNames.forEach(System.out::println);
  }
}
