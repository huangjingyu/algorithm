package huangjingyu.algorithm.practice;

import java.util.Arrays;
import java.util.Vector;

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

	public static void main(String[] args) {
		Vector<Integer> a = new Vector<Integer>();
		a.addAll(Arrays.asList(5,5,5,5,5));
		int ret = minCoins(a, 11);
		System.out.println(ret);
	}
}
