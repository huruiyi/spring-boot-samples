package com.example.time;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.util.Set;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：TimeDifference
 * 日期：2019/5/26 12:53
 **/
public class TimeDifference {
    public static void main(String[] args) {
        Set<String> zones = ZoneId.getAvailableZoneIds();
        for (String item : zones) {
            if (StringUtils.contains(item, "Asia")) {
                System.out.println(item);
            }
        }

        ZoneId singaporeZone = ZoneId.of("Asia/Shanghai");
        ZonedDateTime dateTimeInSingapore = ZonedDateTime.of(LocalDateTime.of(2019, Month.MAY, 26, 12, 54),
                singaporeZone);

        ZoneId aucklandZone = ZoneId.of("America/New_York");
        ZonedDateTime sameDateTimeInAuckland = dateTimeInSingapore.withZoneSameInstant(aucklandZone);

        Duration timeDifference = Duration.between(dateTimeInSingapore.toLocalTime(),
                sameDateTimeInAuckland.toLocalTime());

        System.out.printf("Time difference between %s and %s zones is %d hours", singaporeZone, aucklandZone,
                timeDifference.toHours());
    }
}
