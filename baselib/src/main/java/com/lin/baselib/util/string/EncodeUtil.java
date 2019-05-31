package com.lin.baselib.util.string;

import android.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by linjunqin on 2018/9/24.
 */

public class EncodeUtil {
    public static String encodeMD5(String str) throws NoSuchAlgorithmException {
        if (str == null) return null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        String m=new BigInteger(1, md.digest()).toString(16);
        return m;
    }

    public static String encodeMD5NoE(String str) {
        if (str == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String m=new BigInteger(1, md.digest()).toString(16);
            return m;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeBase64(String s){
        if (s==null) return null;
        return Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
    }

    public static String decodeBase64(String s){
        if (s==null) return null;
        return new String(Base64.decode(s, Base64.NO_WRAP));
    }
}
