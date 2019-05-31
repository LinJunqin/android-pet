package com.lin.baselib.util.string;




import com.lin.baselib.util.LogUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by linjunqin on 2018/9/24.
 */

public class StringUtil {

    public static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static boolean notContainsEmoji(String s){
        if (s==null||s.isEmpty()) return true;
        for(char c:s.toCharArray()){
            if (!isNotEmojiCharacter(c)) return false;
        }
        return true;
    }

    public static String toUtf8String(String s) {
        if (s==null||s.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception e) {
                    LogUtils.e("toUtf8String####",e.getMessage());
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%");
                    sb.append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }


    /*
    *
    *           输入格式验证
    *
    * */
    //手机号
    public static boolean isMobile(String str) {
        Pattern p ;
        Matcher m ;
        boolean b;
        p = Pattern.compile("^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    //号码 手机号 固话均可
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
		/*
		 * 可接受的电话格式有：（手机）
		 */
        String expression = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		/*
		 * 可接受的电话格式有：（座机）
		 */
        String expression2 = "\\d{11}|\\d{12}";
//		CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);

        Pattern pattern2 = Pattern.compile(expression2);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        if (matcher.matches() || matcher2.matches()) {
            isValid = true;
        }
        return isValid;
    }

    //邮箱
    public static boolean checkEmail(String email) {
        boolean flag ;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    // 评论数，点赞数，粉丝数，关注数，文章数等超过1万用xx.xx万表示
    public static String returnMoreNum(int num){
        float result=0;
        if ((float)num>10000){
            result=(float)num/10000;
            return String.format("%.2f",result)+"万";
        }else {
            return String.valueOf(num);
        }

    }

    // 评论数，点赞数，粉丝数，关注数，文章数等超过1万用xx.xx万表示
    public static String returnMoreNum(String num){
        float result=0;
        if (Float.valueOf(num)>10000){
            result=Float.valueOf(num)/10000;
            return String.format("%.2f",result)+"万";
        }else {
            return num;
        }

    }
}
