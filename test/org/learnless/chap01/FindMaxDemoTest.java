package org.learnless.chap01;

import org.junit.Test;

/**
 * Created by learnless on 17.11.28.
 */
public class FindMaxDemoTest {

    @Test
    public void testShape() throws Exception {
        FindMaxDemo.Circle c = new FindMaxDemo.Circle(3.0);
        FindMaxDemo.Square s = new FindMaxDemo.Square(5);
        FindMaxDemo.Rectangle r = new FindMaxDemo.Rectangle(2.5, 4);
        int comp = c.compareTo(s);
        System.out.println(comp);
        int comp2 = s.compareTo(r);
        System.out.println(comp2);
    }


}