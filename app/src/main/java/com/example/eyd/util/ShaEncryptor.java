package com.example.eyd.util;

import android.util.Log;

import java.security.MessageDigest;

public class ShaEncryptor {
    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int twoHalfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9)
                        ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (twoHalfs++ < 1);
        }
        return buf.toString();
    }

    public static String encryptSHA1(String text) {
        String result = "";
        byte[] sha1hash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            result = convertToHex(sha1hash);
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return result.toUpperCase();
    }
}
