package com.payment.gateway.common.utils;

import com.payment.gateway.common.enums.Environment;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateApiKeyId(Environment environment) {

        byte[] bytes = new byte[12];
        RANDOM.nextBytes(bytes);

        String prefix = environment == Environment.PRODUCTION
                ? "pg_live_"
                : "pg_test_";

        return prefix +
                Base64.getUrlEncoder()
                        .withoutPadding()
                        .encodeToString(bytes);
    }

    public static String generateSecretKey(Environment environment) {

        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);

        String prefix = environment == Environment.PRODUCTION
                ? "sk_live_"
                : "sk_test_";

        return prefix +
                Base64.getUrlEncoder()
                        .withoutPadding()
                        .encodeToString(bytes);
    }
}
