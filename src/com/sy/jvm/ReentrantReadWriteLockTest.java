package com.sy.jvm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 * @author ShenYong
 * @date 2020/4/17
 */
public class ReentrantReadWriteLockTest {
    static ReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock readLock = rwLock.readLock();
    static Lock writeLock = rwLock.writeLock();
    static String num = "0";

    public static void main(String[] args) {
        Thread r1 = new Thread(new Reader(), "Reader 1");
        Thread r2 = new Thread(new Reader(), "Reader 2");
        Thread w = new Thread(new Writer(), "Writer");
        r1.start();
        r2.start();
        w.start();
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    readLock.lock();
                    System.out.println(Thread.currentThread().getName() + " --> number is " + num);
                } finally {
                    readLock.unlock();
                }
            }
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 7; i+= 2) {
                try {
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName() + " 正在写入 " + i);
                    num = num.concat(i + "");
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }
}
