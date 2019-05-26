package com.example.time;

import java.time.temporal.ChronoUnit;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：ChronoUnitValues
 * 日期：2019/5/26 14:05
 **/
public class ChronoUnitValues {
    public static void main(String[] args) {
        System.out.println("ChronoUnit DateBased TimeBased Duration");
        System.out.println("---------------------------------------");
        for (ChronoUnit unit : ChronoUnit.values()) {
            System.out.printf("%10s \t %b \t\t %b \t\t %s %n", unit, unit.isDateBased(), unit.isTimeBased(), unit.getDuration());
        }
    }
}
