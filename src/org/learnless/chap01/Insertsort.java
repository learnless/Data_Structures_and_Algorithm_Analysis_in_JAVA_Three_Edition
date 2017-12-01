package org.learnless.chap01;

import java.util.Scanner;

/**
 * Created by learnless on 17.11.28.
 * * 选择问题
 * 从N个数中选出前k个大的数
 * 简单排序：类似插入排序
 * 比冒泡排序好很多，当数组元素且k非常巨大时，仍然不能在合理时间结束
 * TODO 用最小优先队列实现最好
 */
public class Insertsort {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入k的大小:");
        int k = scanner.nextInt();
        System.out.print("请输入数组的数据:");
        int[] a = new int[k];
        String s = "";
        int i = 0;  //数组下标插入位置
        int v = 0;  //输入当前项的值
        while (scanner.hasNext()) {
            s = scanner.next();
            if ("quit".equalsIgnoreCase(s.trim()))
                break;
            v = Integer.valueOf(s);
            sort(a, v, i);
            i = i >= k - 1 ? k - 1 : i + 1; //i等于数组下标时，不再增加
        }

        BubbleSort.print(a);
    }

    public static int[] sort(int[] a, int v, int i) {
        int N = a.length;
        if (i < N) {    //数组插入元素未满，简单插入排序
            if (i == 0) {
                a[i] = v;
                return a;
            }
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] >= v) {
                    if (a[j] > v)  j++;
                    break;
                }
            }
            if (j < 0) {
                j = 0;
            }
            for (; i > j; i--)
                a[i] = a[i - 1];
            a[j] = v;
        } else {
            //使用二分查找
            //首先确定要插入的坐标j ,满足a[j] >= a[j + 1]
            int j = binarySearch(a, 0 , N-1, v);
            for (; i > j; i--) {
                a[i] = a[i - 1];
            }
            a[j] = v;
        }

        return a;
    }

    public static int binarySearch(int[] a, int lo, int hi, int v) {
        if (lo > hi) {
            return lo;
        }

        int mid = lo + (hi - lo) / 2;
        int comp = v - a[mid];
        if (comp > 0) {
            hi = mid - 1;
            return binarySearch(a, lo, hi, v);
        } else if(comp < 0) {
            lo = mid + 1;
            return binarySearch(a, lo, hi, v);
        } else {
            while (mid < a.length - 1 && a[mid] == a[mid + 1])  //如果后边元素相等，指标后移到最后一个元素上
                mid ++;
            return mid;
        }
    }

}
