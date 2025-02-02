package com.eyinfo.foundation.annotations;

import java.lang.annotation.*;

/**
 * public class SourceClass {
 *     String a=10;
 *
 *     //getter setter
 * }
 *
 * public class TargetClass {
 *     @ModelSerializedName("a")
 *     String b;
 *
 *     //getter setter
 * }
 *
 * TargetClass target = ModelConvert.toModel(source,TargetClass.class);
 * ===== OUTPUT TargetClass Result=====
 * {"b": 10}
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModelSerializedName {
    /**
     * 模型待映射的属性名
     * 将原始属性名映射为目标属性的序列化配置,
     * 即在目标模型属性中配置原始模型属性；
     *
     * @return
     */
    String value() default "";
}
