package org.learnless.chap03;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkList的实现
 * Created by learnless on 17.11.30.
 */
public class MyArrayList<T extends Comparable<T>> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private T[] items;

    public MyArrayList() {
        size = 0;
        doClear();
    }

    public void add(T x) {
        add(x, size());
    }

    public void add(T x, int idx) {
        if (items.length == size())
            ensureCapacity(size * 2 + 1);
        for (int i=size; i > idx; i--)
            items[i] = items[i-1];
        items[idx] = x;
        size++;
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return items[idx];
    }

    public T set(int idx, T x) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        T oldItem = get(idx);
        items[idx] = x;
        return oldItem;
    }

    public T remove(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        T oldItem = items[idx];
        for (int i = idx; i < size()-1; i++)
            items[i] = items[i+1];
        size--;
        return oldItem;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size(); i++)
            if (items[i].equals(x))
                return true;
        return false;
    }

    public int size() {
        return size;
    }

    public void trimToSize() {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int size) {
        if (size < this.size)
            return;
        T[] oldItems = items;
        items = (T[]) new Comparable[size];
        for (int i = 0; i < size(); i++)
            items[i] = oldItems[i];
    }

    public void sort() {
        for (int i = 1; i <= size(); i++) {
            //采用插入排序
            for (int j = i; j > 0 && items[j-1].compareTo(items[j])>0; j--) {
                T t = items[j];
                items[j] = items[j-1];
                items[j-1] = t;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayNodeListIterator();
    }

    class ArrayNodeListIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return items[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("e");
        list.add("d");
        list.add("c");
        list.add("g");
        list.add("f");
        list.add("123");
        list.set(0, "aaa");
//        list.trimToSize();
//        list.clear();

        //itrator迭代内部可正常remove
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            if (i++ == 2)
                iterator.remove();
        }

        System.out.println("size = " + list.size());
        list.forEach(l -> {
            System.out.print(l + " ");
        });
        System.out.println("===============sourt()排序后================");
        list.sort();
        for (String s : list) {
            System.out.print(s + " ");
        }
    }
}
