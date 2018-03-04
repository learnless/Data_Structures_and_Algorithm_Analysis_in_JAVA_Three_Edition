package org.learnless.chap05;

import java.util.LinkedList;
import java.util.List;

/**
 * 散列表:分离链接表
 * Created by learnless on 18.3.3.
 */
public class SeparateChainingHashTable<K> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<K>[] lists;    //链表数组
    private int size;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        lists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }
        size = 0;
    }

    public void insert(K k) {
        int hash = hash(k);
        List list = lists[hash];
        if (!list.contains(k)) {
            list.add(k);
            if (++size > lists.length) {
                rehash();
            }
        }
    }

    public void remove(K k) {
        List list = lists[hash(k)];
        if (list.contains(k)) {
            list.remove(k);
            size--;
        }
    }

    public boolean contains(K k) {
        List list = lists[hash(k)];
        return list.contains(k);
    }

    public void rehash() {
        List<K>[] old = lists;
        lists = new LinkedList[nextPrime(2*size)];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }
        size = 0;
        for (int i = 0; i < old.length; i++) {
            for (K k : old[i]) {
                insert(k);
            }
        }
    }

    public int hash(K k) {
        int hashVal = k.hashCode();
        hashVal %= lists.length;
        if (hashVal < 0)
            hashVal += lists.length;
        return hashVal;
    }


    public int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }
        for (; !isPrime(n); n += 2)
            ;
        return n;
    }

    public boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n == 1 || n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void makeEmpty() {
        for (int i = 0; i < lists.length; i++) {
            lists[i].clear();
        }
        size = 0;
    }

    public static void main(String[] args) {
        SeparateChainingHashTable table = new SeparateChainingHashTable(5);
        table.insert(1);
        table.insert(32);
        table.insert(28);
        table.insert(81);
        table.insert(42);
        table.insert(22);
        table.insert(12);

        table.remove(1);

        System.out.println();
    }

}
