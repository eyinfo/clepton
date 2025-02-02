package com.eyinfo.foundation.encrypts;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesGTUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";//加密算法
    private static final byte[] IV = "0000000000000000".getBytes(); //初始化向量

    /**
     * AES 解密操作
     *
     * @param content 待解密内容
     * @param key     加密密钥
     * @return 解密数据
     */
    public static String decrypt(String content, String key) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 配置密码器
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key), ivSpec);
            // 解密
            byte[] result = cipher.doFinal(hex2Bytes(content));
            // 格式化输出
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取密钥,取前16位,不足则复制
     *
     * @param key 用户密钥
     * @return KeySpec对象
     */
    private static SecretKeySpec getSecretKey(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        StringBuilder s = new StringBuilder(key);
        while (s.length() < 16) {
            s.append(key);
        }
        return new SecretKeySpec(s.substring(0, 16).getBytes(), KEY_ALGORITHM);
    }

    /**
     * hex字符串转byte数组
     *
     * @param hex 待转换的hex字符串
     * @return 转换后的byte数组
     */
    private static byte[] hex2Bytes(String hex) {
        if (hex.length() % 2 == 1) {
            hex = "0" + hex;
        }
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return result;
    }
}
