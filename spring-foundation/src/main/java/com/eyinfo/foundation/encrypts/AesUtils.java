package com.eyinfo.foundation.encrypts;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AesUtils {

    private static final String AES = "AES";
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static KeyGenerator keyGenerator = null;

    public static String encrypt(String content, String key) throws Exception {
        keyGenerator = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), AES));
        byte[] b = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        //采用base64算法进行转码，避免出现中文乱码
        return EncryptBase64.encode(b);
    }

    public static String decrypt(String content, String key) throws Exception {
        keyGenerator = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), AES));
        //采用base64算法进行转码，避免出现中文乱码
        byte[] encryptBytes = EncryptBase64.decode(content);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
}
