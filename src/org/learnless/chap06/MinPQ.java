package org.learnless.chap06;

/**
 * 最小优先队列
 * Created by learnless on 18.3.4.
 */
public class MinPQ<Key extends Comparable<? super Key>> {
    private static final int DEFAULT_CAPACITY = 10;
    private Key[] pq;
    private int currentSize;

    public MinPQ() {
        this(DEFAULT_CAPACITY);
    }

    public MinPQ(int size) {
        pq = (Key[]) new Comparable[size + 1];
        currentSize = 0;
    }

    /**
     * 构建优先队列
     * @param items
     */
    public MinPQ(Key[] items) {
        //先将队列下沉使最大值上浮，然后头节点与尾节点对换，再让头节点下沉
        int len = items.length - 1;
        for (int i = len/2; i >= 1; i--)
            sink(items, i, len);
        while (len > 1) {
            change(items, 1, len--);
            sink(items, 1, len);
        }
        pq = items;
        currentSize = items.length - 1;
    }

    public void insert(Key key) {
        check(key);
        pq[++currentSize] = key;
        swin(currentSize);
    }

    public void del(Key key) {
        int k = index(key);
        if (k <= 0) return;
        pq[k] = pq[currentSize];
        pq[currentSize--] = null;
        swin(k);
        sink(k);
    }

    public Key delMin() {
        Key min = pq[1];
        pq[1] = pq[currentSize];
        pq[currentSize] = null;
        currentSize--;
        sink(1);
        return min;
    }

    public Key delMax() {
        Key max = pq[currentSize];
        pq[currentSize] = null;
        currentSize--;
        return max;
    }

    public boolean contains(Key key) {
        return false;
    }

    public int index(Key key) {
        if (key.compareTo(pq[1]) == 0) {
            return 1;
        }
        return index(key, 1);
    }

    private int index(Key key, int i) {
        for (int j = 1; j <= currentSize; j++) {
            if (key.compareTo(pq[j]) == 0) {
                return j;
            }
        }
        return -1;
    }

    public void swin(int k) {
        while (k/2 > 0) {
            if (pq[k].compareTo(pq[k/2]) < 0) {
                change(k, k/2);
            }
            k = k/2;
        }
    }

    public void sink(Key[] items, int k, int len) {
        while (2*k <= len) {
            int t = 2*k;
            if (2*k < len && items[2*k].compareTo(items[2*k+1]) < 0) {
                t += 1;
            }
            if (items[k].compareTo(items[t]) >= 0) {
                break;
            }
            change(items, k, t);
            k = t;
        }
    }

    private void change(Key[] items, int i, int j) {
        Key t = items[i];
        items[i] = items[j];
        items[j] = t;
    }

    public void sink(int k) {
        while (2*k <= currentSize) {
            int t = 2*k;
            if (2*k < currentSize && pq[2*k].compareTo(pq[2*k+1]) > 0) {    //取出最小
                t += 1;
            }
            if (pq[k].compareTo(pq[t]) <= 0) {
                break;
            }
            change(k, t);
            k = 2*k;
        }
    }

    public void change(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public void check(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key isn't empty");
        }
    }

    public static void main(String[] args) {
        MinPQ<Integer> minPQ = new MinPQ<>();
        minPQ.insert(2);
        minPQ.insert(3);
        minPQ.insert(6);
        minPQ.insert(12);
        minPQ.insert(33);
        minPQ.insert(83);
        minPQ.insert(92);
        minPQ.insert(73);
        minPQ.insert(1);

        minPQ.delMin();

        minPQ.del(3);

        //构建队列
        Integer[] array = {null,3,2,93,1,83,42,16,63,59,4};
        MinPQ<Integer> minPQ2 = new MinPQ<>(array);

        System.out.println();
    }


}
