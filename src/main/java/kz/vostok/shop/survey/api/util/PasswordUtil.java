package kz.vostok.shop.survey.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

    public static String hashString(String value) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(value.getBytes());
        return new String(messageDigest.digest());
    }

    public static String forgotPasswordCode(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * 100 % size);
            stringBuilder.append(index);
        }
        return stringBuilder.toString();
    }
}
