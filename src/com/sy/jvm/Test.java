package com.sy.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.*;

/**
 * @author ShenYong
 * @date 2020/3/26
 */
public class Test {

    public static int add() {
        int i = 1;
        int j = 2;
        int res = i + j;
        return res + 10;
    }

    private static PhantomReference<String> reference;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("调用线程：" + Thread.currentThread().getName());
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程 " + Thread.currentThread().getName() + " 在执行任务 " + finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
