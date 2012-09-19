package huangjingyu.algorithm.practice;

public class StringMatch {
	public int match(String str, String sub) {
		char[] arr = str.toCharArray();
		char[] subArr = sub.toCharArray();
		int[] next = buildNext(subArr);
		int i = 0;
		int j = 0;
		while (i <= (arr.length - subArr.length + j)) {
			if (arr[i] != subArr[j]) {
				j = next[j];
				if (j == -1) {
					j = 0;
					i++;
				}
			} else {
				i++;
				j++;
			}
			if (j == subArr.length)
				return i - subArr.length;
		}
		return -1;
	}

	private int[] buildNext(char[] arr) {
		int[] next = new int[arr.length];
		next[0] = -1;
		next[1] = 0;
		for (int i = 2; i < next.length; i++) {
			int j = next[i - 1];
			while (j > -1 && arr[i - 1] != arr[j]) {
				j = next[j];
			}
			j++;
			next[i] = j;
		}
		return next;
	}
}
