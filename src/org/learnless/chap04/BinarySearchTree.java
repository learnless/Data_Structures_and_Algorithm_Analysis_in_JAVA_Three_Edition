package org.learnless.chap04;

import java.util.NoSuchElementException;

/**
 * 二叉查找树
 * Created by learnless on 18.2.15.
 */
public class BinarySearchTree<K extends Comparable<? super K>> {
    class Node {
        private K k;
        private Node left, right;

        public Node(K k) {
            this(k, null, null);
        }

        public Node(K k, Node left, Node right) {
            this.k = k;
            this.left = left;
            this.right = right;
        }

    }

    private Node root;

    public void insert(K k) {
        root = insert(root, k);
    }

    private Node insert(Node n, K k) {
        if (n == null) {
            return new Node(k);
        }

        int comp = k.compareTo(n.k);
        if (comp < 0) {
            n.left = insert(n.left, k);
        } else if (comp > 0) {
            n.right = insert(n.right, k);
        }
        return n;
    }

    public void remove(K k) {
        check(k);
        root = remove(root, k);
    }

    private Node remove(Node n, K k) {
        if (n == null) {
            return null;
        }
        int comp = k.compareTo(n.k);
        if (comp < 0) {
            n.left = remove(n.left, k);
        } else if (comp > 0) {
            n.right = remove(n.right, k);
        } else {
            //从删除的节点右子树查找最小节点来填充，如果右子树为空，从左节点查找最大节点
            if (n.left != null && n.right != null) {
                Node min = findMin(n.right);
                n.k = min.k;
                n.right = remove(n.right, n.k);
            } else {
                n = n.left == null ? n.right : n.left;
            }
        }
        return n;
    }

    public Node find(K k) {
        check();
        return find(root, k);
    }

    private Node find(Node n, K k) {
        if (n == null) {
            return null;
        }
        int comp = k.compareTo(n.k);
        if (comp < 0) {
            return find(n.left, k);
        } else if (comp > 0) {
            return find(n.right, k);
        } else {
            return n;
        }

    }

    public Node findMin() {
        check();
        return findMin(root);
    }

    private Node findMin(Node n) {
        if (n.left == null) {
            return n;
        }
        return findMin(n.left);
    }

    public Node findMax() {
        check();
        return findMax(root);
    }

    private Node findMax(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right == null) {
            return n;
        }
        return findMax(n.right);
    }

    public boolean contains(K k) {
        Node n = find(k);
        return n != null;
    }

    private void printTree() {
        check();
        printTree(root);
    }

    private void printTree(Node n) {
        if (n == null) {
            return;
        }
        System.out.print(n.k + " ");
        printTree(n.left);
        printTree(n.right);
    }


    private void check() {
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }
    }

    private void check(K k) {
        if (k == null) {
            throw new IllegalArgumentException("find the key can't empty");
        }
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }
    }

    public void run() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(4);
        tree.insert(11);
        tree.insert(9);

        tree.remove(1);
        Node node = tree.find(11);
        Node max = tree.findMax();
        Node min = tree.findMin();
        boolean contains = tree.contains(6);
        boolean contains2 = tree.contains(4);
        tree.printTree();
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.run();
    }


}
