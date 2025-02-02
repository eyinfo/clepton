package com.eyinfo.foundation.entity;

import com.eyinfo.foundation.enums.CommonAttrs;
import com.eyinfo.foundation.events.Action;
import com.eyinfo.foundation.utils.ModelConvert;
import com.eyinfo.foundation.utils.ObjectJudge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Result {

    private static final Map<String, BaseResponse> messageConfig = new HashMap<>();

    public static void setMessageConfig(Map<String, BaseResponse> map) {
        if (!ObjectJudge.isNullOrEmpty(map)) {
            for (Map.Entry<String, BaseResponse> entry : map.entrySet()) {
                if (!messageConfig.containsKey(entry.getKey())) {
                    messageConfig.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static <T> ObjectResponse<T> message(int code, String msg, T t) {
        ObjectResponse<T> response = new ObjectResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(t);
        return response;
    }

    public static ObjectResponse<?> message(int code, String msg) {
        return message(code, msg, null);
    }

    public static BaseResponse message(String messageKey) {
        return messageConfig.get(messageKey);
    }

    public static String getMessage(String messageKey) {
        BaseResponse response = messageConfig.get(messageKey);
        return response == null ? "" : response.getMsg();
    }

    public static <T> ObjectResponse<T> success(T t) {
        return message(0, "success", t);
    }

    public static ObjectResponse<?> success() {
        return success(null);
    }

    public static <T extends CommonAttrs> BaseResponse message(T t) {
        return message(t.getCode(), t.getMsg());
    }

    public static <T> ObjectResponse<T> responseObtain(T t) {
        ObjectResponse<T> response = new ObjectResponse<>();
        response.setData(t);
        return response;
    }

    public static <T, E> ObjectExtraResponse<T, E> responseObtain(T t, E extra) {
        ObjectExtraResponse<T, E> response = new ObjectExtraResponse<>();
        response.setData(t);
        response.setExtra(extra);
        return response;
    }

    public static <T, R> PageListResponse<List<R>> response(PageListResponse<List<T>> source, Class<R> targetClass, Action<R> itemAction) {
        PageListResponse<List<R>> response = new PageListResponse<>();
        response.setCount(source.getCount());
        response.setData(ModelConvert.toModels(source.getData(), targetClass, itemAction));
        response.setIsFirstPage(source.isIsFirstPage());
        response.setHasNextPage(source.isHasNextPage());
        return response;
    }

    public static <T, R> PageListResponse<List<R>> response(PageListResponse<List<T>> source, Class<R> targetClass) {
        return response(source, targetClass, null);
    }

    public static <T, R> PageListResponse<List<R>> response(PageListResponse<List<T>> source, Function<List<T>, List<R>> action) {
        PageListResponse<List<R>> response = new PageListResponse<>();
        response.setCount(source.getCount());
        response.setData(action.apply(source.getData()));
        response.setIsFirstPage(source.isIsFirstPage());
        response.setHasNextPage(source.isHasNextPage());
        return response;
    }

    public static <T> PageListResponse<List<T>> response(List<T> list) {
        PageListResponse<List<T>> response = new PageListResponse<>();
        response.setCount(list == null ? 0 : list.size());
        response.setData(list);
        return response;
    }
}
