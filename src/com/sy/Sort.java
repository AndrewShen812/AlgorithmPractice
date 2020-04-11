package com.sy;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    private static int[] genArr() {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i + 1;
        }
        // 打乱顺序
        Random r = new Random(System.currentTimeMillis());
        for (int i = len - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            int iVal = arr[i];
            arr[i] = arr[index];
            arr[index] = iVal;
        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println("org:");
//        int[] arr = genArr();
        int[] arr = {7, 10, 5, 2, 6, 4, 9, 1, 3, 8};
        System.out.println(Arrays.toString(arr));

        System.out.println("begin:");
//        quickSort(arr, 0, arr.length - 1);
        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int keyVal = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] >= keyVal) {
                right--;
            }
            if (left < right) {
                // 此时right的值比关键值小，交换
                System.out.printf(arr[left] + "<-->" + arr[right] + ":");
                swap(arr, left, right);
                System.out.println(Arrays.toString(arr));
            }
            while (left < right && arr[left] < keyVal) {
                left++;
            }
            if (left < right) {
                System.out.printf(arr[left] + "<-->" + arr[right] + ":");
                swap(arr, left, right);
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("left=" + left + ", right=" + right);
        quickSort(arr, start, left - 1);
        quickSort(arr, right + 1, end);
    }

    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

}
