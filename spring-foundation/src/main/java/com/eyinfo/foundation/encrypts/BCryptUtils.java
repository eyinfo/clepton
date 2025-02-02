package com.eyinfo.foundation.encrypts;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    public static String encrypt(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
    }
}
