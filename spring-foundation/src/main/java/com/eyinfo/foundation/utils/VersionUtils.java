package com.eyinfo.foundation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtils {

    //获取版本int值
    //versionName:版本名称
    public static int getVersionValue(String versionName) {
        if (TextUtils.isEmpty(versionName)) {
            return 0;
        }
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(versionName);
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return ConvertUtils.toInt(result.toString());
    }
}
