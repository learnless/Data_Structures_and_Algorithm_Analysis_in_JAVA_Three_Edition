package org.learnless.chap01;

import org.junit.Test;

/**
 * Created by learnless on 17.11.28.
 */
public class InsertsortTest {

    @Test
    public void binarySearch() throws Exception {
        int[] a = {13, 13, 10, 10, 9, 9, 8, 8, 7, 7, 7, 5, 5, 4, 3, 2, 1, 1};
        int i = Insertsort.binarySearch(a, 0, a.length - 1, 7);
        System.out.println(i);
    }

}