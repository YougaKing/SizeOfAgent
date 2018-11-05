package com.region;

public class TextUtils {
    public static boolean isEmpty(String text) {
        if (text == null) {
            return false;
        }
        return text.length() <= 0;
    }
}
