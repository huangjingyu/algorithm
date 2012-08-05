package huangjingyu.algorithm.practice;

public class FindMaxAndMin {
	public int[] find(int[] arr) {
		if (arr.length == 0)
			return null;
		if (arr.length == 1)
			return new int[] { arr[0], arr[0] };
		if (arr.length == 2) {
			if (arr[0] > arr[1])
				return new int[] { arr[0], arr[1] };
			else
				return new int[] { arr[1], arr[0] };
		}
		int max = arr[0];
		int min = arr[1];
		if (max < min) {
			max = arr[1];
			min = arr[0];
		}
		int i = 2;
		while (i < arr.length - 1) {
			int a = arr[i];
			int b = arr[i + 1];
			if (a < b) {
				min = Math.min(min, a);
				max = Math.max(max, b);
			} else {
				min = Math.max(min, b);
				max = Math.max(max, a);
			}
			i += 2;
		}
		if (i < arr.length) {
			if (arr[i] > max) {
				max = arr[i];
			} else {
				min = Math.min(min, arr[i]);
			}
		}
		return new int[] { max, min };
	}
}
