package com.eyinfo.foundation.encrypts;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Sha256 {
    public static String encode(String content) {
        try {
            // 获取MessageDigest实例，并初始化为SHA-256算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 将字符串转换为字节
            byte[] messageBytes = content.getBytes(StandardCharsets.UTF_8);
            // 计算哈希
            byte[] hashBytes = md.digest(messageBytes);
            // 将字节数组转换为十六进制字符串
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hashHex.append('0');
                }
                hashHex.append(hex);
            }
            return hashHex.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
