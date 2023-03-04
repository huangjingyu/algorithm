package huangjingyu.algorithm.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class Coin {
	static int minCoins(Vector<Integer> a, int S) {
		int min = Integer.MAX_VALUE;
		if (S < 0)
			return -1;
		if (S == 0)
			return 0;
		if (a.size() == 0)
			return -1;
		if (a.size() == 1) {
			if (a.get(0) != S)
				return -1;
			else
				return 1;
		}
		for (int i = 0; i < a.size(); i++) {
			int r = minCoins(a, S - a.get(i));
			if (r == -1)
				continue;
			r += 1;
			if (r < min)
				min = r;
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	static int bruteForce(Vector<Integer> a, int S) {
		int[][] cache = new int[a.size()][S + 1];
		for (int i = 0; i < cache.length; i++) {
			cache[i][0] = 0;
		}
		int first = a.get(0);
		for (int i = 1; i <= S; i++) {
			if (i % first == 0) {
				cache[0][i] = i / first;
			} else {
				cache[0][i] = -1;
			}
		}
		for (int i = 1; i < a.size(); i++) {
			for (int k = 1; k <= S; k++) {
				int v = a.get(i);
				if (k < v) {
					cache[i][k] = cache[i - 1][k];
				} else if (k == v) {
					cache[i][k] = 1;
				} else {
					int min = cache[i - 1][k];
					int ind = 1;
					int t = k - v * ind;
					while (t >= 0) {
						if (cache[i - 1][t] != -1) {
							int curChoice = cache[i - 1][t] + ind;
							min = min == -1 ? curChoice : Math.min(min, curChoice);
						}
						ind++;
						t = k - v * ind;
					}
					cache[i][k] = min;
				}
			}
		}
		print(cache);
		return cache[a.size() - 1][S];
	}

	private static void print(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr[0].length; i++) {
			sb.append("\t").append(i);
		}
		System.out.println(sb);
		for (int i = 0; i < arr.length; i++) {
			sb = new StringBuilder();
			sb.append(i).append("\t");
			List<Integer> list = new ArrayList<Integer>();
			for (int a : arr[i]) {
				list.add(a);
			}
			sb.append(StringUtils.join(list, "\t"));
			System.out.println(sb);
		}
	}

	public static void main(String[] args) {
		Vector<Integer> a = new Vector<Integer>();
		a.addAll(Arrays.asList(5, 3, 5, 5, 5));
		int ret = minCoins(a, 11);
		System.out.println(ret);
		ret = bruteForce(a, 11);
		System.out.println(ret);
	}
}
