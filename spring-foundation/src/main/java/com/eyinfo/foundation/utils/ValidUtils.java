package com.eyinfo.foundation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2018/8/26
 * @Description:正则验证类
 * @Modifier:
 * @ModifyContent:
 */
public class ValidUtils {
    public static boolean valid(String pattern, String input) {
        boolean flag = Pattern.matches(pattern, TextUtils.isEmpty(input) ? "" : input);
        return flag;
    }

    public static List<String> matches(String pattern, String input) {
        List<String> lst = new ArrayList<String>();
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(input);
        while (mat.find()) {
            if (mat.groupCount() > 0) {
                String value = mat.group(0);
                if (!TextUtils.isEmpty(value)) {
                    lst.add(value);
                }
            } else {
                String value = mat.group();
                if (!TextUtils.isEmpty(value)) {
                    lst.add(value);
                }
            }
        }
        return lst;
    }

    public static String match(String pattern, String input) {
        List<String> lst = matches(pattern, input);
        if (lst == null || lst.isEmpty()) {
            return "";
        } else {
            return lst.get(0);
        }
    }

    public static List<String> matchValues(String pattern, String matchAttrName, String input) {
        List<String> lst = new ArrayList<String>();
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(input);
        while (mat.find()) {
            //取属性值因此要求匹配到的计数大于1
            int count = mat.groupCount();
            if (count > 1) {
                for (int i = 1; i < count; i++) {
                    String value = mat.group(matchAttrName);
                    if (!TextUtils.isEmpty(value)) {
                        lst.add(value);
                    }
                }
            }
        }
        return lst;
    }
}
