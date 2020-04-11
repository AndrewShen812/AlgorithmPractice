package com.sy;

import java.util.HashMap;

/**
 * 设计和实现一个  LRU (最近最少使用) 缓存机制。
 * <p>实现思路：哈希表+双向链表。哈希表保证了“查询”时间复杂度为O(1),双向链表则保证了删除和插入的时间复杂度可以为O(1)
 * @author ShenYong
 * @date 2020/3/19
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println("put (1, 1):");
        cache.put(1, 1);
        System.out.println("put (2, 2):");
        cache.put(2, 2);
        System.out.println(cache.get(1));
        System.out.println("put (3, 3):");
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println("put (4, 4):");
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    int cnt;
    private HashMap<Integer, Node> map;
    private int cap;

    public LRUCache(int capacity) {
        cnt = 0;
        cap = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    private void print(Node head) {
        System.out.println("当前状态：");
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
            if (head != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node popTail() {
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        return last;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 在已有节点中找到，则把找到的节点更新到头部
        moveToHead(node);
        print(head);

        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(value);

        if (node != null) {
            // 已存在，更新
            node.value = value;
            moveToHead(node);
        } else { // 新添加
            node = new Node(key, value);
            map.put(key, node);
            if (cnt == cap) {
                Node toRemove = popTail();
                map.remove(toRemove.key);
                cnt--;
            }
            addToHead(node);
            cnt++;
        }
        print(head);
    }
}
