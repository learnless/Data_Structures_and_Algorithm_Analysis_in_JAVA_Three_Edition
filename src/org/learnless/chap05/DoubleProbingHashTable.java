package org.learnless.chap05;

/**
 * 散列表：基于平方的散列表
 * Created by learnless on 18.3.3.
 */
public class DoubleProbingHashTable<T> {
    /**
     * 为方便删除操作，使用懒惰删除，元素设置活动设置为false标识
     */
    class Node<T> {
        private T t;
        private boolean isActive;

        public Node(T t) {
            this(t, true);
        }

        public Node(T t, boolean isActive) {
            this.t = t;
            this.isActive = isActive;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    private Node[] array;
    private int currentSize;

    public DoubleProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public DoubleProbingHashTable(int size) {
        array = new Node[nextPrime(size)];
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        currentSize = 0;
    }

    public void insert(T t) {
        int pos = findPos(t);
        if (isActive(pos)) {    //该元素已有
            return;
        }
        array[pos] = new Node(t);

        if (++currentSize >= array.length/2) {
            rehash();
        }
    }

    public void remove(T t) {
        int pos = findPos(t);
        if (isActive(pos)) {
            array[pos].isActive = false;
        }
    }

    /**
     * 查找要插入节点的位置
     * @param t
     * @return
     */
    public int findPos(T t) {
        int offset = 1; //依次查找递增的变量
        int currentPos = hash(t);
        while (array[currentPos] != null && !array[currentPos].t.equals(t)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }

    public void rehash() {
        Node<T>[] old = array;
        array = new Node[nextPrime(2*array.length)];
        currentSize = 0;
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null && old[i].isActive) {
                insert(old[i].t);
            }
        }
    }

    public int hash(T t) {
        int hashVal = t.hashCode();
        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }

    public boolean isActive(int pos) {
        return array[pos] != null && array[pos].isActive;
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

    public static void main(String[] args) {
        DoubleProbingHashTable table = new DoubleProbingHashTable(5);
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
