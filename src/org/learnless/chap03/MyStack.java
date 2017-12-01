package org.learnless.chap03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 栈的实现
 * Created by learnless on 17.12.1.
 */
public class MyStack<T extends Comparable<? super T>> implements Iterable<T> {
    class Node<T> {
        private T elem;
        Node<T> next;
        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    private Node<T> head;   //栈顶
    private int N;  //栈的个数

    public MyStack() {
        N = 0;
    }

    /**
     * 进栈
     * @param x
     */
    public void push(T x) {
        head = new Node(x, head);
        N ++;
    }

    /**
     * 出栈
     * @return
     */
    public T pop() {
        if (head == null)
            return null;
        T elem = head.elem;
        head = head.next;
        N --;
        return elem;
    }

    public boolean Empty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorStack();
    }

    private class IteratorStack implements Iterator<T> {
        private Node<T> innerHead = head;

        @Override
        public boolean hasNext() {
            return innerHead != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T elem = innerHead.elem;
            innerHead = innerHead.next;
            return elem;
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");

        System.out.println("size = " + stack.size());
        for (String s : stack)
            System.out.print(s + " ");

    }
}
