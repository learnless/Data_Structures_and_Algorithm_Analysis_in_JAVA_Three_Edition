package org.learnless.chap06;

import java.util.NoSuchElementException;

/**
 * 左式推:左子树的零路径长大于等于右子树零路径长，具有优先队列性质
 * 零路径:节点到不具有两个子节点的最短路径长
 * Created by learnless on 18.3.4.
 */
public class LeftHeadNode<Key extends Comparable<? super Key>> {
    class Node {
        private Node left;
        private Node right;
        private Key key;
        private int npl;    //零路径长

        public Node(Key key) {
            this(key, null, null);
        }

        public Node(Key key, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.key = key;
            npl = 0;
        }
    }

    private Node root;

    /**
     * 插入，合并操作
     */
    public void insert(Key key) {
        root = merge(new Node(key), root);
    }

    /**
     * 合并操作，若n1为空，返回n2，若n2为空，返回n1
     * 如果n1比n2小,将n2的右子树与n2递归合并,并链接到n1的右子树
     * 若n1比n2大
     * @param n1
     * @param n2
     * @return
     */
    public Node merge(Node n1, Node n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        if (n1.key.compareTo(n2.key) < 0) {
            return merge1(n1, n2);
        } else {
            return merge1(n2, n1);
        }

    }

    /**
     * 合并操作，递归调用，将n2链接到n1的子节点上
     * @param n1
     * @param n2
     * @return
     */
    private Node merge1(Node n1, Node n2) {
        if (n1.left == null)
            n1.left = n2;
        else {
            n1.right = merge(n1.right, n2);
            //交换左右节点
            if (n1.left.npl < n1.right.npl)
                swapChildren(n1);
            //更新npl
            n1.npl = n1.right.npl + 1;
        }
       return n1;
    }

    /**
     * 合并两个对象
     * @param that
     */
    public void merge(LeftHeadNode that) {
        if (this == that) {
            return;
        }
        root = merge(root, that.root);
        that.root = null;
    }

    public Key findMin() {
        check();
        return root.key;
    }

    public Key delMin() {
        check();
        Key key = root.key;

        root = merge(root.left, root.right);

        return key;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 交换左右子节点
     * @param n
     */
    public void swapChildren(Node n) {
        Node t = n.left;
        n.left = n.right;
        n.right = t;
    }

    public void check() {
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }
    }

    public void check(Key k) {
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }
        if (k == null) {
            throw new IllegalArgumentException("element is empty");
        }
    }

    public static void main(String[] args) {
        LeftHeadNode<Integer> leftHeadNode = new LeftHeadNode<>();
        leftHeadNode.insert(3);
        leftHeadNode.insert(10);
        leftHeadNode.insert(8);
        leftHeadNode.insert(21);
        leftHeadNode.insert(14);
        leftHeadNode.insert(17);
        leftHeadNode.insert(23);
        leftHeadNode.insert(26);
        leftHeadNode.insert(1);

        int min = leftHeadNode.delMin();
        int min2 = leftHeadNode.delMin();

        LeftHeadNode<Integer> leftHeadNode2 = new LeftHeadNode<>();
        leftHeadNode2.insert(37);
        leftHeadNode2.insert(3);
        leftHeadNode2.insert(7);
        leftHeadNode2.insert(18);
        leftHeadNode2.insert(35);
        leftHeadNode.merge(leftHeadNode2);

        System.out.println();
    }


}
