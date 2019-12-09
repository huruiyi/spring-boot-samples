package com.example.Time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateDemo {
    public static void main(String[] args) {

    }

    @Test
    public void DateDemo_00() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date end = calendar.getTime();

        System.out.println("start======" + start);
        System.out.println("end========" + end);
    }


    @Test
    public void DateDemo_01() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(calendar.getTime());
        System.out.println(dateStr);

        Date date = simpleDateFormat.parse(dateStr);

        System.out.println(date);
    }

    @Test
    public void DateDemo_02() {
        Date objDate = new Date();
        String strDate;
        DateFormat simpleFormat;
        simpleFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        strDate = objDate.toString();
        System.out.println("\n**********************");
        System.out.println("当前日期和时间");
        System.out.println("**********************\n");
        System.out.println("当前日期： " + strDate);
        System.out.println("\n格式化后： " + simpleFormat.format(objDate));
    }

    @Test
    public void DateDemo_03() {

        Date today = new Date();
        System.out.println("字符串格式：" + today.toString());

        DateFormat f0 = DateFormat.getInstance();
        System.out.println("系统  格式：" + f0.format(today));

        DateFormat f1 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
        DateFormat f2 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
        String s1 = f1.format(today);
        String s2 = f2.format(today);
        System.out.println("中国  格式" + s1);
        System.out.println("中国  格式" + s2);
    }

    @Test
    public void DateDemo_04() {
        System.out.println(CurrentDateTime.getDate());
        System.out.println(CurrentDateTime.getTime());

        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_WEEK, 7);
        System.out.println(today.get(Calendar.YEAR) + "年" + (today.get(Calendar.MONTH) + 1) + "月" + today.get(Calendar.DAY_OF_MONTH) + "日");
    }
}

