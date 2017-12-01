package org.learnless.chap01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by learnless on 17.11.28.
 * 选择问题
 * 从N个数中选出前k个大的数
 * 简单排序：冒泡排序
 */
public class BubbleSort {

    public static int getK(BufferedReader reader) throws IOException {
        System.out.print("请输入k的大小:");
        int k = Integer.parseInt(reader.readLine());
        return k;
    }

    public static Integer[] createArr(BufferedReader reader) throws IOException {
        System.out.print("请输入数组的数据:");
        String s = reader.readLine();
        List<Integer> list = new ArrayList<>();

        for (String s1 : s.trim().split("\\s+")) {
            list.add(Integer.parseInt(s1));
        }
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);

        return arr;
    }

    public static void print(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = getK(reader);
        Integer arr[] = createArr(reader);
        int[] a = sort(arr, k);
        print(a);
    }

    private static int[] sort(Integer[] arr, int k) {
        int[] a = new int[k];
        int N = arr.length;
        for (int i = 0; i < k; i++) {
            //将数组由大到小冒泡，从最后元素进行两两比较，最终最大的元素依次排在前面
            for (int j = N-1; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    int t = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = t;
                }
            }
            a[i] = arr[i];
        }
        return a;
    }

}
