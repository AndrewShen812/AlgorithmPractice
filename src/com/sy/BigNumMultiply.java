package com.sy;

import java.math.BigInteger;
import java.util.Random;

/**
 * 题目：
 * 实现一个简单大数乘法，将一个大数（20位以上）与一个个位数（0-9）相乘，得出计算结果，例如：12345678901234567890 * 7
 *
 * 要求：
 * 1. 不能使用开发语言自带的大数类
 * 2. 程序要支持正负号
 *
 * @author ShenYong
 * @date 2020/4/1
 */
public class BigNumMultiply {
    public static void main(String[] args) {
        String numStr = "-1234567890123456789034567890";
        BigNumMultiply multiply = new BigNumMultiply(numStr);

        int num2 = new Random().nextInt(10);

        System.out.println(numStr + " * " + num2 + " = ");
        System.out.println(multiply.multiply(num2));

        System.out.println("validate：");
        BigInteger integer = new BigInteger(numStr);
        BigInteger intVal = new BigInteger(num2 + "");
        BigInteger res = integer.multiply(intVal);
        System.out.println(res);
    }

    private byte[] digits;
    // 进位值，计算完成后，代表最高位的进位值
    private byte carry;
    private boolean isNegtive = false;
    private static final String NUM_PATTERN = "^[-+]?\\d+$";
    private static final String SIGN_PATTERN = "^[-+]\\d+$";

    public BigNumMultiply(String num) {
        digits = parse(num);
    }

    /**
     * 验证输入合法性
     * @param numStr
     * @throws NumberFormatException
     */
    private void validateInput(String numStr) throws NumberFormatException {
        if (numStr == null) {
            throw new NumberFormatException("input is null.");
        }
        if (!numStr.matches(NUM_PATTERN)) {
            throw new NumberFormatException(numStr + " is not a valid int number.");
        }
    }

    private byte[] parse(String numStr) throws NumberFormatException {
        validateInput(numStr);
        if (numStr.matches(SIGN_PATTERN)) {
            isNegtive = numStr.startsWith("-");
            numStr = numStr.substring(1);
        }
        byte[] nums = new byte[numStr.length()];
        for (int i = 0; i < numStr.length(); ++i) {
            char ch = numStr.charAt(i);
            nums[i] = (byte) Character.digit(ch, 10);
        }
        return nums;
    }

    public String multiply(int num2) {
        byte[] res = _multiply(num2);
        StringBuffer sb = new StringBuffer(isNegtive ? "-" : "");
        // 最高位进位
        if (carry > 0) {
            sb.append((char)(carry + '0'));
        }
        for (int i = 0; i < res.length; ++i) {
            sb.append((char)(res[i] + '0'));
        }
        return sb.toString();
    }

    private byte[] _multiply(int num2) {
        if (num2 == 0) {
            digits = new byte[] {0};
            return digits;
        }
        if (num2 == 1) {
            return digits;
        }
        // 进位值
        carry = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            byte curVal = (byte) (digits[i] * num2 + carry);
            digits[i] = (byte) (curVal % 10);
            carry = (byte) (curVal / 10);
        }
        return digits;
    }
}
