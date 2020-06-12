package com.sy.jvm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShenYong
 * @date 2020/4/16
 */
public class ReentrantLockTest implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    // 公平锁
    private ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
        ReentrantLockTest test1 = new ReentrantLockTest();
        ReentrantLockTest test2 = new ReentrantLockTest();
//        new Thread(() -> test1.print()).start();
//        new Thread(() -> test1.print()).start();

        // 公平锁测试
        new Thread(test1).start();
        new Thread(test1).start();
        new Thread(test1).start();
    }

    private void print() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        } finally {
            lock.unlock();
        }
    }

    //---------------- 公平锁测试 ----------------
    @Override
    public void run() {
        print2();
    }

    private int sharedNum = 0;
    private void print2() {
        while (sharedNum < 10) {
            try {
                fairLock.lock();
                sharedNum++;
                System.out.println(Thread.currentThread().getName() + "获得锁，sharedNum is " + sharedNum);
            } finally {
                fairLock.unlock();
            }
        }
    }

}
