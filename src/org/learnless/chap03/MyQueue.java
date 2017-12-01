package org.learnless.chap03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列实现，采用收尾链接数组简单实现
 * Created by learnless on 17.12.1.
 */
public class MyQueue<T extends Comparable<? super T>> implements Iterable<T> {
    private static final int DEFAULT_SIZE = 10;

    private int front = -1, rear = -1;
    private T[] items;
    private int N;

    public MyQueue() {
        this(DEFAULT_SIZE);
    }

    public MyQueue(int size) {
        items = (T[]) new Comparable[size];
        N = 0;
    }

    public void enqueue(T x) {
        if (rear == -1)
            front = 0;
        if (N == items.length - 1)
            resize(items.length * 2);
        rear = (rear+1) % items.length;

        items[rear] = x;
        N ++;
    }

    public T dequeue() {
        if (rear == -1)
            throw new NoSuchElementException();
        T elem = items[front];
        front = (front + 1) % items.length;
        N --;
        if (N ==  items.length / 4)
            resize(items.length / 2);
        return elem;
    }

    private void resize(int size) {
        T[] newItems = (T[]) new Comparable[size];
        int i = -1;
        for (; front % items.length != rear + 1; front++) {
            newItems[++i] = items[front % items.length];
        }
        front = 0;
        rear = i;
        items = newItems;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorStack();
    }

    private class IteratorStack implements Iterator<T> {
        private T[] innerItems;
        private int innerFront = front;
        private int innerRear  = rear;
        private int innerN = N;

        public IteratorStack() {
            innerItems = items;
        }

        @Override
        public boolean hasNext() {
            if (innerN == 0)
                return false;
            return true;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            T t = innerItems[front];
            innerFront = (innerFront + 1) % innerItems.length;
            innerN --;
            return t;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("b");
        queue.enqueue("a");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("g");
        queue.enqueue("h");
        queue.enqueue("i");
        queue.enqueue("j");
        queue.enqueue("k"); //8
//        queue.enqueue("l");
//        queue.enqueue("m");
//        queue.enqueue("n");
//        queue.enqueue("o");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
//        queue.enqueue("6");

        System.out.println("size = " + queue.size());
        for (String s : queue)
            System.out.print(s + " ");
    }
}
