package com.sy.thread;

public class SleepAndWait {

    public static void main(String[] args) {
        SleepAndWait sleepAndWait = new SleepAndWait();
        new Thread(() -> {
            sleepAndWait.sleepOrWait();
        }).start();
        new Thread(() -> {
            sleepAndWait.func();
        }).start();
    }

    private void sleepOrWait() {
        synchronized (this) {
            System.out.println("1. before block");
            try {
                // 1. 验证 sleep 不会释放锁
//                Thread.sleep(500);

                // 2. 验证wait会释放锁
                // 因为 wait 释放了锁，所以并发时 func 会立即得到执行
                this.wait();
                System.out.println("3. after block");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void func() {
        synchronized (this) {
            System.out.println("2. call func");
            this.notify();
            try {
                Thread.sleep(2000);
                System.out.println("4. func done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
