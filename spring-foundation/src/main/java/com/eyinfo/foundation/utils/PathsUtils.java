package com.eyinfo.foundation.utils;

import java.io.File;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2018/4/22
 * @Description:
 * @Modifier:
 * @ModifyContent:
 */
public class PathsUtils {
    /**
     * 组合路径
     * <p>
     *
     * @param paths    要组合的地址列表
     * @param isEscape 是否对/进行转义
     * @return
     */
    public static String combine(boolean isEscape, String... paths) {
        File file = new File(paths[0]);
        for (int i = 1; i < paths.length; i++) {
            file = new File(file, paths[i]);
        }
        String uri = file.getPath();
        if (!uri.contains("://") && uri.contains(":/")) {
            uri = uri.replace(":/", "://");
        }
        if (isEscape) {
            return uri.replace("/", "\\/");
        }
        return uri;
    }

    public static String combine(String... paths) {
        return combine(false, paths);
    }
}
