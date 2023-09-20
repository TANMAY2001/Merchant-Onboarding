package com.example.MerchantOnboarding.Utils;
import org.apache.commons.lang3.RandomStringUtils;
public class Utils {
    public static String UUIDGenerator() {
        return  RandomStringUtils.randomAlphabetic(3).toUpperCase() + RandomStringUtils.
                randomNumeric(3);
    }

    public static String PasswordGenerator(){
        String password = RandomStringUtils.randomAlphanumeric(6,10);
        return password;
    }
    public static String Encryption(String S) {
        byte[] hash=S.getBytes();
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
