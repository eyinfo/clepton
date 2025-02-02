package com.eyinfo.foundation.events;

public interface OnRedisPropertyCall {

    /// 主机地址
    String getHost();

    /// 端口
    int getPort();

    /// 获取密码
    String getPassword();
}
