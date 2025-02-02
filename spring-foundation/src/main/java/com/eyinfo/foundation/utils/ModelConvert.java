package com.eyinfo.foundation.utils;

import com.eyinfo.foundation.annotations.ModelSerializedName;
import com.eyinfo.foundation.events.Action;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 特殊字段映射使用注解{@ModelSerializedName}
 */
public class ModelConvert {

    private static <T, RT> T shallowCopy(RT source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(source, targetClass);
    }

    private static <T, RT> T deepCopy(RT source, Class<T> targetClass) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(targetClass, source.getClass()).byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(source, targetClass);
    }

    /**
     * 实体对象映射
     *
     * @param source      数据源
     * @param targetClass 目标类class
     * @param deep        true-做深copy;false-做㳀copy;
     * @param <T>         目标类类型
     * @param <RT>        数据源类型
     * @return 目标对象
     */
    public static <T, RT> T toModel(RT source, Class<T> targetClass, boolean deep) {
        if (source == null) {
            return null;
        }
        if (targetClass == HashMap.class) {
            String json = JsonUtils.toStr(source);
            return JsonUtils.parseT(json, targetClass);
        }
        T obj = null;
        if (deep) {
            obj = deepCopy(source, targetClass);
        } else {
            obj = shallowCopy(source, targetClass);
        }
        if (obj != null) {
            specialFieldResolution(source, obj);
            return obj;
        }
        return JsonUtils.newNull(targetClass);
    }

    private static <T, RT> void specialFieldResolution(RT source, T target) {
        Class<?> targetClass = target.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ModelSerializedName.class)) {
                ModelSerializedName serializedName = field.getAnnotation(ModelSerializedName.class);
                String sourceFieldName = serializedName.value();
                Object sourceValue = GlobalUtils.getPropertiesValue(source, sourceFieldName);
                GlobalUtils.setPropertiesValue(target, field.getName(), sourceValue);
            }
        }
    }

    /**
     * 实体对象映射
     *
     * @param source      数据源
     * @param targetClass 目标类class
     * @param <T>         目标类类型
     * @param <RT>        数据源类型
     * @return 目标对象
     */
    public static <T, RT> T toModel(RT source, Class<T> targetClass) {
        return toModel(source, targetClass, false);
    }

    public static <T, RT> List<T> toModels(List<RT> source, Class<T> targetClass, boolean deep, Action<T> itemAction) {
        if (source == null) {
            return null;
        }
        List<T> items = new ArrayList<>();
        source.forEach(m -> {
            T model = toModel(m, targetClass, deep);
            if (itemAction == null) {
                specialFieldResolution(m, model);
            } else {
                itemAction.call(model);
            }
            items.add(model);
        });
        return items;
    }

    public static <T, RT> List<T> toModels(List<RT> source, Class<T> targetClass, boolean deep) {
        return toModels(source, targetClass, deep, null);
    }

    public static <T, RT> List<T> toModels(List<RT> source, Class<T> targetClass, Action<T> itemAction) {
        return toModels(source, targetClass, false, itemAction);
    }

    public static <T, RT> List<T> toModels(List<RT> source, Class<T> targetClass) {
        return toModels(source, targetClass, false, null);
    }

    public static <T> List<T> toModels(Object source, Class<T> targetClass) {
        String content = JsonUtils.toStr(source);
        return JsonUtils.parseArray(content, targetClass);
    }
}
