package com.zuba.carsharing.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^09\\d{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4})$");

    //驗證手機號碼是否符合規則
    public static boolean isMatchPhone(String phone) {
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }

    //驗證E-mail是否符合規則
    public static boolean isMatchEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
