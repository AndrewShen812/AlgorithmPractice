package com.sy;

/**
 * @author ShenYong
 * @date 2020/4/9
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "abc";
        String str2 = new String("abc");
        System.out.println(str == str2);
        str = str + "def";
        System.out.println(str);
    }
}
