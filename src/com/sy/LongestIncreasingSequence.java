package com.sy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 最长上升子序列长度问题
 * @author ShenYong
 * @date 2020/3/18
 */
public class LongestIncreasingSequence {
    public static void main(String[] args) {
//        int[] arr = {10,9,2,5,3,7,101,18};
//        int[] arr = {10,11,12,15,23,7,8,9};
//        int[] arr = {4,10,4,3,8,9};
        int[] arr = {2,2};
        System.out.println(lis2(arr));
    }

    // O(n^2)
    private static int lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 1;
        int[] dp = new int[arr.length];
        // 初始化状态数组
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
            System.out.println(Arrays.toString(dp));
        }

        return max;
    }

    // O(nlogn)
    private static int lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        List<Integer> lis = new ArrayList<>();
        lis.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
            } else {
                int left = 0;
                int right = lis.size() - 1;
                int index = 0;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    int midVal = lis.get(mid);
                    if (arr[i] <= midVal) {
                        right = mid - 1;
                    } else if (arr[i] > midVal) {
                        index = right;
                        left = mid + 1;
                    }
                }
                lis.set(index, arr[i]);
            }
            System.out.println(Arrays.toString(lis.toArray()));
        }
        return lis.size();
    }
}
