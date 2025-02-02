package com.eyinfo.foundation.utils;

public class DowngradeConvert {

    //Integer to int
    public static int toInt(Integer value) {
        return value == null ? 0 : value;
    }

    //Byte to int
    public static int toInt(Byte value) {
        return value == null ? 0 : value.intValue();
    }

    //Long to long
    public static long toLong(Long value) {
        return value == null ? 0 : value;
    }

    //Double to double
    public static double toDouble(Double value) {
        return value == null ? 0 : value;
    }

    //Float to float
    public static float toFloat(Float value) {
        return value == null ? 0f : value;
    }

    //Byte to byte
    public static byte toByte(Byte value) {
        return value == null ? 0 : value;
    }
}
