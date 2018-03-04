import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by learnless on 17.11.28.
 */
public class Test1 {

    @Test
    public void t1() throws Exception {
        int[] a = {1,2,3,4,5};
        sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    @Test
    public void t2() throws Exception {
        String s = null;
        if ("".equals(s)) {
            System.out.println(11);
        }
    }

    @Test
    public void t3() throws Exception {
        Collection c = new ArrayList<>();
        for (int i = 1; i < 1; i++)
            System.out.println(i);

    }

    @Test
    public void t4() throws Exception {
        int i = 0;
        for (print('A'); print('C') && i < 2; print('D')) {
            i++;
            System.out.print('T');
            print('B');
        }
    }

    private static boolean print(char c) {
        System.out.print(c);
        return true;
    }

    private static int[] sort(int[] a) {
        a[0] = 2;
        return null;
    }


}
