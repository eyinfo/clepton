package com.eyinfo.foundation.annotations;


import com.eyinfo.foundation.enums.VerifyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRequired {

    //true-登录验证;false-不作验证；
    boolean required() default true;

    //token授权验证类型
    VerifyType verify() default VerifyType.both;
}
