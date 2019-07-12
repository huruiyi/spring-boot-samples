package com.example.Time;

import java.time.Instant;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：UsingInstant
 * 日期：2019/5/26 14:06
 **/
public class UsingInstant {
    public static void main(String args[]) {
        // prints the current timestamp with UTC as time zone
        Instant currTimeStamp = Instant.now();
        System.out.println("Instant timestamp is: " + currTimeStamp);

        // prints the number of seconds as Unix timestamp from epoch time
        System.out.println("Number of seconds elapsed: " + currTimeStamp.getEpochSecond());

        // prints the Unix timestamp in milliseconds
        System.out.println("Number of milliseconds elapsed: " + currTimeStamp.toEpochMilli());
    }
}
