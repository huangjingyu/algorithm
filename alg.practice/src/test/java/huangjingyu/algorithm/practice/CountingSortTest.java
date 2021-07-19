package huangjingyu.algorithm.practice;


import org.junit.Test;

public class CountingSortTest extends TestBase {
    CountingSort t = new CountingSort();

    @Test
    public void test1() {
        int[] arr = new int[]{3, 9, 2, 3, 5, 1, 0, 9, 8};
        int[] ret = t.sort(arr, 10);
        for (int a : ret) {
            System.out.println(a);
        }
    }
}
