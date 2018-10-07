package com.ruiyi;

public class Test02 {
    public static void main(String[] args) {
        System.out.println();
        int year = 2018;
        int month = 10;
        int week = getWeek(year, month, 1);


        System.out.println("日	一	二	三	四	五	六");
        System.out.println();
        int count = 0;
        for (int i = 0; i < week; i++) {
            count++;
            System.out.print("\t");
        }
        int days = getDays(year, month);
        for (int i = 1; i <= days; i++) {
            count++;
            System.out.print(i + "\t");
            if (count == 7) {
                System.out.println();
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }


    public static int getWeek(int year, int month, int days) {
        int totalDays = getTotalDays(year, month, days);
        int week = (year - 1 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400 + totalDays) % 7;
        return week;
    }


    /**
     * 给定年月日，求这个日子是这一年的第多少天
     */
    public static int getTotalDays(int year, int month, int days) {
        //定义一个天数的和    31+29+24
        int totalDays = 0;
        //从1月循环到当前月-1, 把这每一个月的天数求和，加上当前的月的号
        for (int i = 1; i < month; i++) {
        //求每一个月的天数
            int mdays = getDays(year, i);
            totalDays += mdays;
        }
        //加上当前月的号（24）
        totalDays += days;
        return totalDays;
    }


    public static int getDays(int year, int month) {
        //定义一个月的天数的变量
        int mdays = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                mdays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                mdays = 30;
                break;
            //可以被400整除或者能被4整除但是不能被100整除
            case 2:
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    mdays = 29;
                } else {
                    mdays = 28;
                }
                break;
        }

        return mdays;

    }
}
