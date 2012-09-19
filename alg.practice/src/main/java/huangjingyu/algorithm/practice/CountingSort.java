package huangjingyu.algorithm.practice;

public class CountingSort {
    public int[] sort(int[] arr, int k) {
	int[] ret = new int[arr.length];
	int[] c = new int[k];
	for (int a : arr) {
	    c[a]++;
	}
	for (int i = 1; i < c.length; i++) {
	    c[i] = c[i - 1] + c[i];
	}
	for (int i = arr.length - 1; i >= 0; i--) {
	    ret[c[arr[i]] - 1] = arr[i];
	    c[arr[i]]--;
	}
	return ret;
    }
}
