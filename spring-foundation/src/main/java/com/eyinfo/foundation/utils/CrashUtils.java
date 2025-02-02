package com.eyinfo.foundation.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Author lijinghuan
 * Email:ljh0576123@163.com
 * CreateTime:2019/3/15
 * Description:crash信息
 * Modifier:
 * ModifyContent:
 */
public class CrashUtils {
    /**
     * 获取错误信息
     *
     * @param throwable 堆栈
     * @return 转换成string后的堆栈信息
     */
    public static String getCrashInfo(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        // printStackTrace(PrintWriter s)
        // 将此 throwable 及其追踪输出到指定的 PrintWriter
        throwable.printStackTrace(printWriter);
        // getCause() 返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null。
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        return result;
    }

    //获取异常消息
    private static String getMessage(Throwable throwable, int count) {
        if (throwable == null) {
            return "";
        }
        String message = throwable.getMessage();
        if (TextUtils.isEmpty(message)) {
            Throwable cause = throwable.getCause();
            if (count <= 3) {
                return getMessage(cause, count + 1);
            }
            return cause == null ? "" : cause.getMessage();
        } else {
            return message;
        }
    }

    public static String getMessage(Throwable throwable) {
        return getMessage(throwable, 3);
    }
}
