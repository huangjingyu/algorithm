package huangjingyu.algorithm.practice.crack;

import java.util.Arrays;

public class P19_11 {
    public static void printPairSums(int[] arr, int sum) {
	Arrays.sort(arr);
	int i = 0;
	int j = arr.length - 1;
	while (i < j) {
	    int s = arr[i] + arr[j];
	    if (s == sum) {
		System.out.println(arr[i] + "-" + arr[j]);
		i++;
		j--;
	    } else if (s > sum) {
		j--;
	    } else {
		i++;
	    }
	}
    }

    public static void main(String[] args) {
	printPairSums(new int[] { -2, -1, 0, 3, 5, 6, 7, 9, 13, 14 }, 12);
    }
}
