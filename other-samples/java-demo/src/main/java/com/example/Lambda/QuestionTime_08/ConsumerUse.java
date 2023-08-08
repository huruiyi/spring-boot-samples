package com.example.Lambda.QuestionTime_08;/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import com.rabbitmq.client.UnblockedCallback;
import java.util.function.ObjIntConsumer;

class ConsumerUse {

  public static void main(String[] args) {
    ObjIntConsumer<String> charAt = (str, i) -> str.charAt(i); // #1
    UnblockedCallback java = () -> charAt.accept("java", 1);
    System.out.println();          // #2
  }
}
