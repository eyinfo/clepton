package com.eyinfo.foundation.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2017/12/9
 * @Description:
 * @Modifier:
 * @ModifyContent:
 */
public class ConvertUtils {

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

    /**
     * 数字Object对象转int型
     * <p>
     * param obj          要转换的对象
     * param defaultValue 默认值
     * return
     */
    public static int toInt(Object obj, int defaultValue) {
        int val = defaultValue;
        try {
            if (obj != null) {
                String objstr = obj.toString().trim();
                if (!TextUtils.isEmpty(objstr)) {
                    Number number = NumberFormat.getNumberInstance().parse(objstr);
                    val = number.intValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            val = defaultValue;
        }
        return val;
    }

    /**
     * 数字Object对象转int型(默认值0)
     * <p>
     * param obj 要转换的对象
     * return
     */
    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static int toInt(long value) {
        return (int) value;
    }

    /**
     * 数字Object对象转long型
     * <p>
     * param obj          要转换的对象
     * param defaultValue 默认值
     * return
     */
    public static long toLong(Object obj, int defaultValue) {
        long val = defaultValue;
        try {
            if (obj != null) {
                String objstr = obj.toString().trim();
                if (!TextUtils.isEmpty(objstr)) {
                    Number number = NumberFormat.getNumberInstance().parse(objstr);
                    val = number.longValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            val = defaultValue;
        }
        return val;
    }

    /**
     * 数字Object对象转long型
     * <p>
     * param obj 要转换的对象
     * return
     */
    public static long toLong(Object obj) {
        return toLong(obj, 0);
    }

    /**
     * 数字Object对象转double型
     * <p>
     * param obj          要转换的对象
     * param defaultValue 默认值
     * return
     */
    public static double toDouble(Object obj, double defaultValue) {
        double val = defaultValue;
        try {
            if (obj != null) {
                String objstr = obj.toString().trim();
                if (!TextUtils.isEmpty(objstr)) {
                    Number number = NumberFormat.getNumberInstance().parse(objstr);
                    val = number.doubleValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            val = defaultValue;
        }
        return val;
    }

    /**
     * 数字Object对象转double型(默认值1.0)
     * <p>
     * param obj 要转换的对象
     * return
     */
    public static double toDouble(Object obj) {
        return toDouble(obj, 1.0);
    }

    /**
     * 数字Object对象转float型
     * <p>
     * param obj          要转换的对象
     * param defaultValue 默认值
     * return
     */
    public static float toFloat(Object obj, float defaultValue) {
        float val = defaultValue;
        try {
            if (obj != null) {
                String objstr = obj.toString().trim();
                if (!TextUtils.isEmpty(objstr)) {
                    Number number = NumberFormat.getNumberInstance().parse(objstr);
                    val = number.floatValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            val = defaultValue;
        }
        return val;
    }

    /**
     * 数字Object对象转float型(默认值1.0)
     * <p>
     * param obj 要转换的对象
     * return
     */
    public static float toFloat(Object obj) {
        return toFloat(obj, 1);
    }

    /**
     * 转字符串
     * <p>
     * param value
     * return
     */
    public static String toString(Object value) {
        return value != null ? value.toString() : "";
    }

    public static String toAmount(String format, double amount) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(amount);
    }

    public static <T> List<T> toList(String content, String split) {
        List<T> lst = new ArrayList<T>();
        if (!TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(split)) {
                content = content.trim();
                String[] args = content.split(split);
                if (!ObjectJudge.isNullOrEmpty(args)) {
                    for (String arg : args) {
                        if (!TextUtils.isEmpty(arg)) {
                            lst.add((T) arg);
                        }
                    }
                }
            }
        }
        return lst;
    }

    /**
     * 将列表根据split作为分隔符进行连接
     *
     * @param lst   要连接的集合
     * @param split 连接分隔符
     * @return
     */
    public static String toJoin(List<String> lst, String split) {
        if (ObjectJudge.isNullOrEmpty(lst)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (String item : lst) {
            buffer.append(item + split);
        }
        if (buffer.length() > 0) {
            buffer.delete(buffer.length() - split.length(), buffer.length());
        }
        return buffer.toString();
    }

    public static String toJoin(Set<String> sets, String split) {
        if (ObjectJudge.isNullOrEmpty(sets)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (String item : sets) {
            buffer.append(item + split);
        }
        if (buffer.length() > 0) {
            buffer.delete(buffer.length() - split.length(), buffer.length());
        }
        return buffer.toString();
    }

    /**
     * 根据split连接params参数
     *
     * @param split  连接分隔符
     * @param params 要连接的内容
     * @return
     */
    public static String toJoin(String split, String... params) {
        if (ObjectJudge.isNullOrEmpty(params)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (String param : params) {
            if (!TextUtils.isEmpty(param)) {
                buffer.append(param.trim() + split);
            }
        }
        if (buffer.length() > 0) {
            buffer.delete(buffer.length() - split.length(), buffer.length());
        }
        return buffer.toString();
    }

    /**
     * 将列表每一项用startSplit和endSplit包裹进行连接
     *
     * @param lst        要连接的集合
     * @param startSplit 每一项开始连接符
     * @param endSplit   每一项开始连接符
     * @return
     */
    public static String toJoin(List<String> lst, String startSplit, String endSplit) {
        if (ObjectJudge.isNullOrEmpty(lst)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String param : lst) {
            builder.append(String.format("%s%s%s", startSplit, param, endSplit));
        }
        return builder.toString().trim();
    }

    /**
     * 泛型可变参数转成list
     *
     * @param params 泛型可变参数
     * @param <T>    参数类型
     * @return list数组
     */
    public static <T> List<T> toList(T... params) {
        if (ObjectJudge.isNullOrEmpty(params)) {
            return new ArrayList<T>();
        }
        return Arrays.asList(params);
    }

    /**
     * 添加字符串 到可变参数集合中
     *
     * @param object 字符串对象
     * @param params 参数集合
     * @return
     */
    public static String[] toJoinArray(String object, String... params) {
        if (object == null || ObjectJudge.isNullOrEmpty(params)) {
            return null;
        }
        String[] array = new String[params.length + 1];
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            array[i] = param;
        }
        array[params.length] = object;
        return params;
    }

    public static Object toObject(byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            bis.close();
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    public static byte[] toBytes(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            oos.close();
            bos.close();
            return bytes;
        } catch (IOException ex) {
            return null;
        }
    }

    private static Map<Integer, String> getFormatSymbol(String content) {
        Map<Integer, String> map = new LinkedHashMap<>();
        if (ObjectJudge.isEmptyString(content)) {
            return map;
        }
        List<String> symbols = new LinkedList<String>() {{
            add("\r");
            add("\n");
            add("\\s");
            add("\\S");
            add("\t");
            add(" ");
        }};
        for (int i = 0; i < content.length(); i++) {
            String s = ConvertUtils.toString(content.charAt(i));
            if (symbols.contains(s)) {
                map.put(i, s);
            }
        }
        return map;
    }

    /// 格式恢复
    /// [source] 源内容
    /// [restoreContent] 待恢复的内容
    public static String formatRestore(String source, String restoreContent) {
        Map<Integer, String> formatSymbol = getFormatSymbol(source);
        if (ObjectJudge.isEmptyString(restoreContent)) {
            return "";
        }
        if (ObjectJudge.isNullOrEmpty(formatSymbol)) {
            return restoreContent;
        }
        StringBuilder builder = new StringBuilder(restoreContent);
        for (Map.Entry<Integer, String> entry : formatSymbol.entrySet()) {
            builder.insert(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }

    /// 驼峰转下划线
    public static String toUnderline(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // to sets
    public static Set<String> toSets(String... elements) {
        Set<String> sets = new HashSet<>();
        if (elements == null) {
            return sets;
        }
        for (String element : elements) {
            sets.add(element);
        }
        return sets;
    }

    public static Set<String> toSetsSplit(String content, String split) {
        Set<String> lst = new HashSet<>();
        if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(split)) {
            content = content.trim();
            String[] args = content.split(split);
            for (String arg : args) {
                if (!TextUtils.isEmpty(arg)) {
                    lst.add(arg);
                }
            }
        }
        return lst;
    }

    //转换sql字符串（如：'xx','xx','xx'）
    public static String toSqlString(Collection<String> elements) {
        StringBuilder builder = new StringBuilder();
        if (!ObjectJudge.isNullOrEmpty(elements)) {
            int size = elements.size();
            int pos = 0;
            Iterator<String> iterator = elements.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                builder.append("'").append(next).append("'");
                if ((pos + 1) < size) {
                    builder.append(",");
                }
                pos++;
            }
        }
        return builder.toString();
    }
}
