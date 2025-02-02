package com.eyinfo.foundation.encrypts;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class EncryptUtils {
    // 密钥(DES加密和解密过程中，密钥长度都必须是8的倍数)
    private final static String secretKey = "e6943fa893182808";

    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     */
    public static String encode(String plainText) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(secretKey.getBytes());
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//DES加密和解密过程中，密钥长度都必须是8的倍数
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // using DES in ECB mode
        Cipher cipher = Cipher.getInstance("DES/ECB/pkcs5padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        // 执行加密操作
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return EncryptBase64.encode(encryptData);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     */
    public static String decode(String encryptText) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(secretKey.getBytes());
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // using DES in ECB mode
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
        // 正式执行解密操作
        byte[] decryptData = cipher.doFinal(EncryptBase64.decode(encryptText));
        return new String(decryptData, encoding);
    }

    public static String padding(String str) {
        byte[] oldByteArray;
        oldByteArray = str.getBytes(StandardCharsets.UTF_8);
        int numberToPad = 8 - oldByteArray.length % 8;
        byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
        System.arraycopy(oldByteArray, 0, newByteArray, 0,
                oldByteArray.length);
        for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
            newByteArray[i] = 0;
        }
        return new String(newByteArray, StandardCharsets.UTF_8);
    }
}
