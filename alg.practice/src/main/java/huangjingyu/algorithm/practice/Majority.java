package huangjingyu.algorithm.practice;

public class Majority {
	public int find(boolean[][] know) {
		if (know.length == 1)
			return 0;
		int candidate = -1;
		int i = 0;
		int j = 1;
		int next = 2;
		while (next <= know.length) {
			if (know[i][j]) {
				i = next;
			} else {
				j = next;
			}
			next++;
		}
		if (i == know.length) {
			candidate = j;
		} else {
			candidate = i;
		}
		for (int k = 0; k < candidate; k++) {
			if (know[candidate][k] || !know[k][candidate])
				return -1;
		}
		for (int k = candidate + 1; k < know.length; k++) {
			if (know[candidate][k] || !know[k][candidate])
				return -1;
		}
		return candidate;
	}
}
