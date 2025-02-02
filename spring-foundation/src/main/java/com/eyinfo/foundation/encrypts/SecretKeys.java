package com.eyinfo.foundation.encrypts;

import com.eyinfo.foundation.CommonException;
import com.eyinfo.foundation.utils.GlobalUtils;
import com.eyinfo.foundation.utils.TextUtils;

import java.nio.charset.StandardCharsets;

/**
 * 密钥key生成
 */
public class SecretKeys {

    private static String genSecret(String reference) {
        String reduceString = ReduceArithmetic.getReduceString(reference);
        try {
            String encode = EncryptUtils.encode(reduceString);
            if (encode.endsWith("=")) {
                return encode.replaceAll("\\r|\\n|\\s|\\t", "");
            }
            String result = EncryptBase64.encode(encode.getBytes(StandardCharsets.UTF_8));
            return result.replaceAll("\\r|\\n|\\s|\\t", "");
        } catch (Exception e) {
            return genSecret(reference);
        }
    }

    /**
     * 密钥key生成
     *
     * @return scret key
     */
    public static String genSecretKey(String ivKey) {
        if (TextUtils.isEmpty(ivKey)) {
            throw new CommonException(new Throwable("生成密钥的ivKey不可为空"));
        }
        return genSecret(ivKey);
    }

    /**
     * 密钥key生成
     *
     * @return scret key
     */
    public static String genSecretKey() {
        String guid = GlobalUtils.getNewGuid();
        return genSecretKey(guid);
    }
}
