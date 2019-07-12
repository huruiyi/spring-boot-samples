package com.example.Time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：CustomTimePatterns
 * 日期：2019/5/26 14:08
 **/
// Using examples, illustrates how to use "pattern strings" for creating custom time formats
class CustomTimePatterns {
    public static void main(String[] args) {
        // patterns from simple to complex ones
        String[] timeFormats = {
                "h:mm",         /* h is hour in am/pm (1-12), m is minute */
                "hh 'o''clock'", /* '' is the escape sequence to print a single quote */
                "H:mm a",       /* H is hour in day (0-23), a is am/pm*/
                "hh:mm:ss:SS",  /* s is seconds, S is milliseconds */
                "K:mm:ss a"     /* K is hour in am/pm(0-11) */
        };
        LocalTime now = LocalTime.now();
        for (String timeFormat : timeFormats) {
            System.out.printf("Time in pattern \"%s\" is %s %n", timeFormat, DateTimeFormatter.ofPattern(timeFormat).format(now));
        }
    }
}
