package com.eyinfo.foundation.utils;

public class SystemUtils {
    /**
     * Spring Boot会自动设置一个系统属性spring.main.debuggable，当程序以调试模式启动时，这个属性会被设置为true。
     *
     * @return true-debug模式启动，反之release模式启动；
     */
    public static boolean isDebug() {
        String debugMode = System.getProperty("spring.main.debuggable");
        return "true".equals(debugMode);
    }
}
