package org.learnless.chap01;


/**
 * Created by learnless on 17.11.28.
 * 查找对象数组最大者
 */
public class FindMaxDemo<T> {

    public static Comparable findMax(Comparable[] arr) {
        Comparable t = arr[0];
        for (Comparable c : arr)
            if (c.compareTo(t) > 0.0)
                t = c;

        return t;
    }

    public T findMax(T[] arr, Comparator<? super T> cmp) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0)
                maxIndex = i;
        }

        return arr[maxIndex];
    }

    /**
     * 函数对象，为了某个方法创建一个类
     * 忽视大小写比较
     */
    static class CaseInsensitiveCompare implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareToIgnoreCase(rhs);
        }
    }


    public static void main(String[] args) {
        Shape[] shapes = {new Circle(2.0), new Square(4.0), new Rectangle(3.0, 4.0)};
        System.out.println(findMax(shapes));
        String[] strings = {"abc", "bbc", "aaa"};
        System.out.println(findMax(strings));

        FindMaxDemo<String> demo = new FindMaxDemo<>();
        String[] strings2 = {"A", "C", "c"};
        System.out.println(demo.findMax(strings2, new CaseInsensitiveCompare()));
    }

    static class Shape implements Comparable {
        private double area;

        public double area() {
            return this.area;
        }
        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Shape)) {
                throw new RuntimeException("类型不符合有求");
            }
            return (int) (this.area() - ((Shape)o).area());
        }

        @Override
        public String toString() {
            return "Shape{" +
                    "area=" + area() +
                    '}';
        }
    }

    static class Circle extends Shape {
        private double r;

        public Circle(double r) {
            this.r = r;
        }

        public double area() {
            return Math.PI * r * r;
        }

    }

    static class Square extends Shape {
        private double r;

        public Square(double r) {
            this.r = r;
        }

        public double area() {
            return r * r;
        }
    }

    static class Rectangle extends Shape {
        private double r1, r2;

        public Rectangle(double r1, double r2) {
            this.r1 = r1;
            this.r2 = r2;
        }

        @Override
        public double area() {
            return r1 * r2;
        }
    }





}
