package com.sy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 借助LinkedHashMap实现的LRUCache算法
 * @author ShenYong
 * @date 2020/3/19
 */
public class LRUCacheLHM extends LinkedHashMap<Integer, Integer> {

    public static void main(String[] args) {
        LRUCacheLHM cache = new LRUCacheLHM(2);
        System.out.println("put (1, 1)");
        cache.put(1, 1);
        System.out.println("put (2, 2)");
        cache.put(2, 2);
        System.out.println("get 1:" + cache.get(1));
        System.out.println("put (3, 3)");
        cache.put(3, 3);
        System.out.println("get 2:" + cache.get(2));
        System.out.println("put (4, 4)");
        cache.put(4, 4);
        System.out.println("get 1:" + cache.get(1));
        System.out.println("get 3:" + cache.get(3));
        System.out.println("get 4:" + cache.get(4));
    }

    private int capacity;

    public LRUCacheLHM(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
