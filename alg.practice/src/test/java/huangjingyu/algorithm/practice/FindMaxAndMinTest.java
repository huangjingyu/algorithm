package huangjingyu.algorithm.practice;

import junit.framework.TestCase;
import org.junit.Test;

public class FindMaxAndMinTest extends TestCase {

    @Test
    public void test1() {
        FindMaxAndMin t = new FindMaxAndMin();
        int[] arr = new int[]{8, 2, 3, 9, 11, 2, 5};
        int[] ret = t.find(arr);
        assertEquals(11, ret[0]);
        assertEquals(2, ret[1]);
    }
}
