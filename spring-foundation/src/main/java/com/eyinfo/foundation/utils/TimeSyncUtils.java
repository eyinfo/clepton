package com.eyinfo.foundation.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 时间戳同步工具类
 * UTC（协调世界时）：所有服务都应使用UTC时间，在存储和传输时间戳时使用UTC，可以有效避免由于时区差异导致的时间混淆。
 */
public class TimeSyncUtils {

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间对象
     *
     * @return Calendar
     */
    public static long getUTCTimestampMillis() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    /**
     * 获取UTC时间戳
     *
     * @param utcFormatTime utc格式化的时间字符串
     * @return 时间戳
     */
    public static long getUTCTimestampMillis(String utcFormatTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime parse = LocalDateTime.parse(utcFormatTime, formatter);
        return parse.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    /**
     * 获取当前格式化UTC时间字符串
     *
     * @return 示例:2024-09-19 03:28:45.956
     */
    public static String getUTCFormatTime() {
        long utcTimestamp = getUTCTimestampMillis();
        return getUTCFormatTime(utcTimestamp);
    }

    /**
     * 获取UTC格式化UTC时间字符串
     *
     * @param timestamp 时间戳
     * @return 示例:2024-09-19 03:28:45.956
     */
    public static String getUTCFormatTime(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return localDateTime.format(formatter);
    }
}
