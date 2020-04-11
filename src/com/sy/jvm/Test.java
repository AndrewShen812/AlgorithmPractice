package com.sy.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

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
        String str = new String("abc");
        ReferenceQueue refQueue = new ReferenceQueue();
        reference = new PhantomReference<String>(str, refQueue);
//        WeakReference<String> reference = new WeakReference<String>(str, refQueue);
        str = null;
//        System.out.println("reference.get after set null:" + reference.get());
        System.out.println("refQueue.poll before gc:" + refQueue.poll());
        System.gc();
        System.out.println("reference.get after gc:" + reference.get());
        System.out.println("refQueue.poll after gc:" + refQueue.poll());
//        String s = reference.get();
    }
}
