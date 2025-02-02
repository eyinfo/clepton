package com.eyinfo.foundation.utils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2017/12/9
 * @Description:
 * @Modifier:
 * @ModifyContent:
 */
public class ObjectJudge {
    /**
     * 判断列表是否为空
     * <p>
     * param list 需要检测的列表集合
     * return
     */
    public static <T> Boolean isNullOrEmpty(T[] list) {
        if (list != null && list.length > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param list 需要检测的列表集合
     * return
     */
    public static <K, V> Boolean isNullOrEmpty(TreeMap<K, V> list) {
        if (list != null && !list.isEmpty() && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param hasList 需要检测的列表集合
     * return
     */
    public static Boolean isNullOrEmpty(HashSet<?> hasList) {
        if (hasList != null && !hasList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param hasList 需要检测的列表集合
     * return
     */
    public static Boolean isNullOrEmpty(HashMap<?, ?> hasList) {
        if (hasList != null && !hasList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param hasList 需要检测的列表集合
     * return
     */
    public static Boolean isNullOrEmpty(Map<?, ?> hasList) {
        if (hasList != null && !hasList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param hasList 需要检测的列表集合
     * return
     */
    public static Boolean isNullOrEmpty(Hashtable<?, ?> hasList) {
        if (hasList != null && !hasList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param list 整型数据集合
     * return
     */
    public static Boolean isNullOrEmpty(int[] list) {
        if (list != null && list.length > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param longList 长整型集合
     * return
     */
    public static Boolean isNullOrEmpty(long[] longList) {
        if (longList != null && longList.length > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param list 列表集合
     * return
     */
    public static Boolean isNullOrEmpty(List<?> list) {
        if (list != null && !list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param list 数据集合
     * return
     */
    public static Boolean isNullOrEmpty(Collection<?> list) {
        if (list != null && !list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断列表是否为空
     * <p>
     * param list 数据列表
     * return
     */
    public static Boolean isNullOrEmpty(String[][] list) {
        if (list != null && list.length > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isChineseChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    private static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (char c : ch) {
            if (isChineseChar(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否中文字符
     * <p>
     * param chineseStr
     * return
     */
    public static boolean isChineseCharacter(String chineseStr) {
        String regex = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(chineseStr);
        boolean matches = m.matches();
        if (matches) {
            boolean b = chineseStr.length() == 1 && Character.isSpaceChar(chineseStr.charAt(0));
            if (b) {
                return false;
            } else {
                return true;
            }
        }
        p = Pattern.compile("[\uFF01]|[\uFF0C-\uFF0E]|[\uFF1A-\uFF1B]|[\uFF1F]|[\uFF08-\uFF09]|[\u3001-\u3002]|[\u3010-\u3011]|[\u201C-\u201D]|[\u2013-\u2014]|[\u2018-\u2019]|[\u2026]|[\u3008-\u300F]|[\u3014-\u3015]");
        m = p.matcher(String.valueOf(chineseStr));
        if (m.find()) {
            return false;
        }
        boolean chinese = isChinese(chineseStr);
        if (chinese) {
            if (chineseStr.length() == 1 && Character.isSpaceChar(chineseStr.charAt(0))) {
                return false;
            } else {
                return true;
            }
        }
        return chinese;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * 是否为真
     * <p>
     * param object true或大于0都为真,否则为假
     * return
     */
    public static boolean isTrue(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Boolean) {
            return Boolean.parseBoolean(String.valueOf(object));
        } else {
            String flag = object.toString().trim().toLowerCase();
            if (TextUtils.equals(flag, "true")) {
                return true;
            } else if (ConvertUtils.toInt(flag) > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isBankCard(String bankCard) {
        if (bankCard == null) {
            return false;
        }
        if (bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0 || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static boolean isEmptyString(String content) {
        if (TextUtils.isEmpty(content)) {
            return true;
        } else {
            content = content.toLowerCase();
            if (TextUtils.equals(content, "null") || TextUtils.equals(content, "(null)")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 是否运行在docker容器中
     *
     * @return true/false
     */
    public static Boolean isRunningInDocker() {
        try (Stream<String> stream = Files.lines(Paths.get("/proc/1/cgroup"))) {
            return stream.anyMatch(line -> line.contains("/docker"));
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断是否数字字符
     *
     * @param str 比较判断字符串
     * @return true-数字字符；false-非数字字符；
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否html内容
     *
     * @param text 文本内容
     * @return true-html数据；false-非html数据，如文本或json数据
     */
    public static boolean isHtml(String text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        String reg = "<(\\\"[^\\\"]*\\\"|'[^']*'|[^'\\\">])*>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 添加序列化对象判断
     *
     * @param obj 待验证对象
     * @return true-可序列化；false-不可序列化;
     */
    public static boolean isSerializable(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            if (Serializable.class.isAssignableFrom(clazz)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }

    /**
     * 判断数字是否为空或0
     *
     * @param number Number
     * @param <T>
     * @return
     */
    public static <T extends Number> boolean isNullOrZero(T number) {
        if (number == null) {
            return true;
        }
        if (number instanceof Long) {
            return number.longValue() == 0;
        } else if (number instanceof Float) {
            return number.floatValue() == 0;
        } else if (number instanceof Double) {
            return number.doubleValue() == 0;
        } else if (number instanceof Integer) {
            return number.intValue() == 0;
        } else if (number instanceof Byte) {
            return number.byteValue() == 0;
        } else if (number instanceof Short) {
            return number.shortValue() == 0;
        }
        return false;
    }
}
