package com.sy.jvm;

/**
 * @author ShenYong
 * @date 2020/4/16
 */
public class Synchronized implements Runnable {

    private int num = 0;

    private synchronized void inc() {
        num += 1;
    }

    private Object lock = new Object();

    public static void main(String[] args) {
        Synchronized sync1 = new Synchronized();
        Synchronized sync2 = new Synchronized();

        // 对象方法加锁
//        new Thread(() -> sync1.print()).start();
//        new Thread(() -> sync1.print()).start();
        // 静态方法加锁
//        new Thread(() -> sync1.print2()).start();
//        new Thread(() -> sync2.print2()).start();
        // 代码块加对象锁
//        new Thread(() -> sync1.print3()).start();
//        new Thread(() -> sync1.print3()).start();
        // 代码块以Class加锁。等同于加 static synchronized 修饰符
        new Thread(() -> sync1.print4()).start();
        new Thread(() -> sync1.print4()).start();
    }

    private synchronized void print() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is printing " + i);
        }
    }

    private static synchronized void print2() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is printing " + i);
        }
    }

    private synchronized void print3() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        }
    }

    private synchronized void print4() {
        synchronized (Synchronized.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        }
    }

    @Override
    public void run() {

    }
}
