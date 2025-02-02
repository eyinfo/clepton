package com.eyinfo.foundation.utils;

import com.eyinfo.foundation.GsonParameterizedType;
import com.eyinfo.foundation.IntegerDeserializer;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2017/12/6
 * @Description:
 * @Modifier:
 * @ModifyContent:
 */
public class JsonUtils {
    /**
     * 单位缩进字符串。
     */
    private static String SPACE = "   ";

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE);
            gsonBuilder.setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE);
            gsonBuilder.registerTypeAdapter(Integer.class, new IntegerDeserializer());
            gson = gsonBuilder.create();
        }
        return gson;
    }

    public static String toStr(Object object) {
        if (object == null) {
            return "";
        }
        Gson gson = getGson();
        return gson.toJson(object);
    }

    public static <T> T parseT(String json, Class<T> clazz) {
        try {
            Gson gson = getGson();
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            return newNull(clazz);
        }
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        try {
            Gson gson = getGson();
            Type type = new GsonParameterizedType(clazz);
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            return new ArrayList<T>(0);
        }
    }

    public static <T> T newNull(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // json parase error
        }
        return null;
    }

    /**
     * 获取json指定key对应的值
     *
     * @param keyName            json键
     * @param jsonString         json
     * @param isFilterWhitespace true-过滤空格;false-不做过滤;
     * @return 返回对应的值
     */
    public static String getValue(String keyName, String jsonString, boolean isFilterWhitespace) {
        if (ObjectJudge.isEmptyString(keyName) || ObjectJudge.isEmptyString(jsonString)) {
            return "";
        }
        if (isFilterWhitespace) {
            jsonString = jsonString.replaceAll("\\r|\\n|\\s|\\t", "");
        }
        String regex = "((\"" + keyName + "\")|('" + keyName + "')):(((\\[(.+)\\](\\,|\\}))|(\\{(.+)\\}(\\,|\\})))|((.*?)((\\,|\\})(\\s\\S)*)))";
        Matcher matcher = Pattern.compile(regex).matcher(jsonString);
        String value = "";
        while (matcher.find()) {
            //避免null出错
            value = (matcher.group() + "").trim();
            //根据:分隔
            int index = value.indexOf(":");
            if (index < 0 || (index + 1) == value.length()) {
                continue;
            }
            value = value.substring(index + 1).trim();
            int start = 0;
            if (value.startsWith("\"") || value.startsWith("'")) {
                start = 1;
            }
            //去掉前面引号
            //去掉最后一个字符包含的,或}
            value = value.substring(start, value.length() - 1);
            value = value.trim();
            //去掉后面引号
            int end = value.length();
            if (value.endsWith("\"") || value.endsWith("'")) {
                end -= 1;
            }
            value = value.substring(0, end);
            break;
        }
        return value;
    }

    /**
     * 获取json指定key对应的值
     *
     * @param keyName    json键
     * @param jsonString json
     * @return 返回对应的值
     */
    public static String getValue(String keyName, String jsonString) {
        return getValue(keyName, jsonString, true);
    }

    /**
     * 获取json指定key对应的值
     *
     * @param relationKeys       json键关系(如key或key->key1->key2),根据json关系指定
     * @param jsonString         json
     * @param isFilterWhitespace true-过滤空格;false-不做过滤;
     * @return 返回对应的值
     */
    public static String getAccurateValue(String relationKeys, String jsonString, boolean isFilterWhitespace) {
        if (ObjectJudge.isEmptyString(relationKeys) || isEmpty(jsonString)) {
            return "";
        }
        String subJson = "";
        if (isFilterWhitespace) {
            subJson = jsonString.replaceAll("\\r|\\n|\\s|\\t", "");
        } else {
            subJson = jsonString;
        }
        String[] split = relationKeys.split("->");
        if (ObjectJudge.isNullOrEmpty(split)) {
            return "";
        }
        String value = "";
        for (int i = 0; i < split.length; i++) {
            if ((i + 1) == split.length) {
                value = getValue(split[i], subJson, isFilterWhitespace);
            } else {
                subJson = getValue(split[i], subJson, isFilterWhitespace);
            }
        }
        return value;
    }

    public static String getAccurateValue(String relationKeys, String jsonString) {
        return getAccurateValue(relationKeys, jsonString, true);
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;

        // 遍历输入字符串。
        for (int i = 0; i < length; i++) {
            // 1、获取当前字符。
            key = json.charAt(i);

            // 2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                // （1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                // （2）打印：当前字符。
                result.append(key);

                // （3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                // （4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                // （5）进行下一次循环。
                continue;
            }

            // 3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                // （1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                // （2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                // （3）打印：当前字符。
                result.append(key);

                // （4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',' || json.charAt(i + 1) != '"')) {
                    result.append('\n');
                }

                // （5）继续下一次循环。
                continue;
            }

            // 4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            // 5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    public static boolean isJson(String json) {
        if (ObjectJudge.isEmptyString(json)) {
            return true;
        }
        json = json.replaceAll("\\r|\\n|\\s|\\t", "");
        //判断是否为json格式{...}或[...]
        String regex = "^(\\{(.*)\\})$|^(\\[(.*)\\])$";
        if (!ValidUtils.valid(regex, json)) {
            return false;
        }
        Gson gson = getGson();
        TypeAdapter<JsonElement> strictAdapter = gson.getAdapter(JsonElement.class);
        try {
            strictAdapter.fromJson(json);
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 检测json字符串是否为空
     *
     * @param json json字符串
     * @return
     */
    public static boolean isEmpty(String json) {
        if (!isJson(json)) {
            return true;
        }
        json = json.replaceAll("\\r|\\n|\\s|\\t", "");
        //如果对象直接包含数组如{["id",3,"name":"名称"]}
        String regex = "^(\\{\\[)(.+)(\\]\\})$";
        if (ValidUtils.valid(regex, json)) {
            return false;
        } else {
            //如果数组中包含对象
            regex = "^(\\[\\{)(.+)(\\}\\])$";
            if (ValidUtils.valid(regex, json)) {
                return false;
            } else {
                //如果只对象
                HashMap map = JsonUtils.parseT(json, HashMap.class);
                if (map != null && map.size() > 0) {
                    return false;
                } else {
                    int length = json.replace(" ", "").trim().length();
                    if (length > 2) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    /**
     * 判断json数据是否为数组类型
     *
     * @param json json data
     * @return true-数组类型；false-非数组；
     */
    public static boolean isArray(String json) {
        if (!isJson(json)) {
            return false;
        }
        String regex = "^(\\[(.*)\\])$";
        return ValidUtils.valid(regex, json);
    }
}
