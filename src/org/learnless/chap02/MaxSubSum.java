package org.learnless.chap02;

/**
 * Created by learnless on 17.11.28.
 * 数组最大子序列
 * 四种实现方式
 */
public class MaxSubSum {

    /**
     * 算法1
     * 最暴力查找，多层嵌套,穷举:
     * 对数组[0]:
     * [0]
     * [0]+[1]
     * ...
     * [0]+[1]+...+[N-1]
     * 对数组[1]:
     * [1]
     * [1] + [2]
     * [1] + [2] + [3]
     * ...
     * [1] + [2] + ... + [N-1]
     *
     * ...到
     * [N-1]
     * 故最后穷举所有可能出现的结果，可获取最大的子序列
     * @return
     */
    public static int[] maxSubSum1(int[] a) {
        int N = a.length - 1;
        int start = -1, end = -1;
        int max = 0;

        for (int i = 0; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                int curMax = 0;
                for (int k = i; k <= j; k++) {
                    curMax += a[k];
                }
                if (curMax > max) {
                    max = curMax;
                    start = i;
                    end = j;
                }
            }
        }
        int[] newArray = new int[end - start + 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = a[start++];
        }
        System.out.println("max = " + max);
        return newArray;
    }

    /**
     * 算法2
     * 对算法1 j不回溯，减少K这层循环
     * @param a
     * @return
     */
    public static int[] maxSubSum2(int[] a) {
        int N = a.length -1;
        int start = 0, end = 0;
        int max = 0;
        for (int i = 0; i <= N; i++) {
            int curMax = 0;
            for (int j = i; j <= N; j++) {
                curMax += a[j];
                if (curMax > max) {
                    start = i;
                    end = j;
                    max = curMax;
                }
            }
        }

        int[] newArray = new int[end - start + 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = a[start++];
        }
        System.out.println("max = " + max);
        return newArray;
    }

    /**
     * 算法3
     * 采用分治思想
     * 最大子序列出现的情况，要么在数组的左边，要么出现在数组的右边，要么跨越中部出现在左右两边
     * 前面两种情况好分析，可采用递归求解，
     * 而第三种情况比较复杂，可以通过求出左边最大的序列[包含最右元素]，右边最大的序列[包含最左边元素]，从而求出
     * @param a
     * @return
     */
    public static int maxSubSum3(int[] a, int lo, int hi) {
        if (lo == hi) {
            if (a[lo] > 0)
                return a[lo];
            else
                return 0;
        }

        int mid = (lo + hi) / 2;
        int maxL = maxSubSum3(a, lo, mid);
        int maxR = maxSubSum3(a, mid + 1, hi);

        //求左边最大序列
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = mid; i >= lo; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        //求右边最大子序列
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = mid + 1; i <= hi; i++) {
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max3(maxL, maxR, maxLeftBorderSum + maxRightBorderSum);   //从三个数中获取最大值
    }


    /**
     * 算法4
     * 技巧性，正确性不易看出，得经过严格的推导证明求解
     * 在i...j数组叠加中，此时若总和小于，可直接将i推到j+1
     * 令k在i,j之间，则开始于k的任意子序列都不大于从i到p-1的子序列(好好推敲)
     * 由于p到j的子序列不可能为负，so
     * @param a
     * @return
     */
    public static int maxSubSum4(int[] a) {
        int N = a.length - 1;
        int maxSum = 0, thisSum = 0;
        int start = 0, end = 0;
        int restart = 0;    //记录当前序列小于的下个起始坐标
        for (int i = 0; i <= N; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
                start = restart;
                end = i;
            } else if(thisSum < 0) {
                thisSum = 0;
                restart = i + 1;
            }
        }
        System.out.println(start+ ", "+end);
        return maxSum;
    }

    public static void main(String[] args) {
//        int[] a = {1, 292, 19, -29, 31, -40, 32, -23, 1, -2, 32, -3, -7, 6};
        int[] a = {1, 3, -1, 2, -1, 2, -2, 3};
//        int[] subSum1 = maxSubSum1(a);
//        int[] subSum1 = maxSubSum2(a);
//        System.out.println(maxSubSum3(a, 0, a.length - 1));
        System.out.println(maxSubSum4(a));

//        print(subSum1);
    }

    private static void print(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int max3(int num1, int num2, int num3) {
        return num1 > num2 ? (num1 > num3 ? num1 : num3) : (num2 > num3 ? num2 : num3);
    }

}
