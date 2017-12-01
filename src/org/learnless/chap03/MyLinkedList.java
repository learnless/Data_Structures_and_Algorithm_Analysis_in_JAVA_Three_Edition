package org.learnless.chap03;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList实现
 * 使用双链表，首尾节点默认都为空，方便操作
 * Created by learnless on 17.12.1.
 */
public class MyLinkedList<T extends Comparable<? super T>> implements Iterable<T> {
    class Node<T> {
        T elem;
        Node<T> prev, next;

        public Node(T elem, Node<T> prev, Node<T> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head, rear;
    private int size;
    private int modCount;   //操作次数

    public MyLinkedList() {
        doClear();
    }

    private void doClear() {
        head = new Node(null, null, null);
        rear = new Node(null, head, null);
        head.next = rear;
        size = 0;
        modCount ++;
    }

    public void clear() {
        doClear();
    }

    public void add(T x) {
        add(size(), x);
    }

    public void add(int idx, T x) {
        //二分查找
        Node<T> node = getNode(idx, 0, size());
        addBefore(getNode(idx, 0, size()), x);
    }

    private void addBefore(Node<T> node, T x) {
        Node<T> newNode = new Node<>(x, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size ++;
        modCount ++;
    }

    public T set(int idx, T t) {
        Node<T> node = getNode(idx);
        T oldElem = node.elem;
        node.elem = t;
        modCount ++;
        return oldElem;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    public T remove(Node<T> node) {
        T oldElem = node.elem;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size --;
        modCount ++;
        return oldElem;
    }

    public Node<T> remove(T x) {
        for (Node<T> t = head.next; t != null && t.elem != null; t = t.next)
            if (t.elem.compareTo(x) == 0) {
                remove(t);
                return t;
            }
        modCount ++;
        return null;
    }

    public Node<T> getNode(int idx) {
        return getNode(idx, 0, size()-1);
    }

    private Node<T> getNode(int idx, int lo, int hi) {
        Node<T> p;
        if (lo < 0 || idx > hi)
            throw new IndexOutOfBoundsException();
        if (idx < size() / 2) {
            p = head.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = rear;
            for (int i = size(); i > idx ; i--)
                p = p.prev;
        }

        return p;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T x) {
        for (Node<T> t = head.next; t != null && t.elem !=null; t = t.next)
            if (t.elem.compareTo(x) == 0)
                return true;
        return false;
    }

    public void sort() {
        //// TODO: 17.12.1  
        //对链接进行排序，可以新建个list，然后在循环旧的list,在新list头部插入,类似插入排序
        Node<T> newHead = new Node(null, null, null);
        Node<T> newRear = new Node(null, newHead, null);
        newHead.next = newRear;
        newHead.next = head.next;
        Node<T> p = newHead.next;
        Node node;
        for (Node<T> t = head.next.next; t != null; t = t.next) {
            int comp = t.elem.compareTo(p.elem);
            if (comp > 0) {

            } else if (comp < 0) {  //当前元素最小，直接插入头节点前
                node = t;
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head.next;
        private int innerModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != rear;
        }

        @Override
        public T next() {
            if (modCount != innerModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            T oldElm = current.elem;
            current = current.next;
            okToRemove = true;
            return oldElm;
        }

        @Override
        public void remove() {
            if (modCount != innerModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            innerModCount ++;
            okToRemove = false;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList();
        list.add("a");
        list.add("b");
        list.add("e");
        list.add("d");
        list.add("c");
        list.add("g");
        list.add("f");
        list.add("123");
        list.set(0, "aaaa");
        list.add("456");
        list.add(4, "0");
        list.add(list.size(), "789");
        list.add(0, "0");
        list.remove(0);
        list.remove("789");
        System.out.println(list.contains("11"));

        System.out.println("size = " + list.size() + " , modCount = " + list.modCount);
        /**
         * 增强for内部不能移除list
         */
        for (String s : list) {
            System.out.print(s + " ");
//            if (s.equals("b"))
//                list.remove(s); //error
        }

        System.out.println("\n============使用iterator迭代，内部可remove===============");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.print(s + " ");
            if (s.equals("b"))
                iterator.remove();
        }

        System.out.println("\n============使用iterator remove后的list===============");
        for (String s : list)
            System.out.print(s + " ");


    }
}
