package huangjingyu.algorithm.practice;


public class CountingSortTest extends BaseTest {
    CountingSort t = new CountingSort();

    public void test1() {
	int[] arr = new int[] { 3, 9, 2, 3, 5, 1, 0, 9, 8 };
	int[] ret = t.sort(arr, 10);
	for(int a : ret) {
	    System.out.println(a);
	}
    }
}
