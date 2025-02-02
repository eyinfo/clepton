package com.eyinfo.foundation.enums;

import com.eyinfo.foundation.utils.TextUtils;

public enum Environment {
    none,
    dev,
    test,
    pre,
    prod;

    public static Environment getEnvironment(String name) {
        Environment environment = null;
        Environment[] values = Environment.values();
        for (Environment value : values) {
            if (TextUtils.equals(value.name(), name)) {
                environment = value;
                break;
            }
        }
        return environment;
    }
}
