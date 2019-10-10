package com.wzb.businessservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Satsuki
 * @time 2019/10/10 20:39
 * @description:
 * 正则表达式匹配截取数字
 */
public class RegexUtil {
//    // 判断一个字符串是否都为数字
//    public boolean isDigit(String strNum) {
//        return strNum.matches("[0-9]{1,}");
//    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) strNum);
        return matcher.matches();
    }

    //截取数字(只能截取第一次找到的一串数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static void main(String[] args) {
        String a="love23next234csdn3423javaeye";
        String b = "￥120";
        System.out.println(RegexUtil.getNumbers(a));
        System.out.println(RegexUtil.getNumbers(b));
    }
}
