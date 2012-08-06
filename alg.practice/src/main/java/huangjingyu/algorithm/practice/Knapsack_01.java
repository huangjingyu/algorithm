package huangjingyu.algorithm.practice;

public class Knapsack_01 {
    public static class Item {
	int w;
	int v;
    }

    public static class Solution {
	int v;
	boolean used;

	public Solution(int _v, boolean _used) {
	    v = _v;
	    used = _used;
	}
    }

    public int maxValue(Item[] items, int w) {
	Solution[][] sarr = new Solution[items.length][w + 1];
	for (int k = 0; k <= w; k++) {
	    if (k < items[0].w) {
		sarr[0][k] = new Solution(0, false);
	    } else if (k == items[0].w) {
		sarr[0][k] = new Solution(items[0].v, true);
	    } else {
		sarr[0][k] = new Solution(items[0].v, false);
	    }
	}
	for (int i = 1; i < items.length; i++) {
	    for (int k = 0; k <= w; k++) {
		if (k == 0) {
		    sarr[i][k] = new Solution(0, false);
		} else {
		    int a = sarr[i - 1][k].v;
		    int b = -1;
		    if (k - items[i].w >= 0) {
			b = sarr[i - 1][k - items[i].w].v + items[i].v;
		    }
		    if (a > b) {
			sarr[i][k] = new Solution(a, false);
		    } else {
			sarr[i][k] = new Solution(b, true);
		    }
		}
	    }
	}
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i <= w; i++) {
	    sb.append("  \t").append(i);
	}
	System.out.println(sb);
	for (int i = 0; i < items.length; i++) {
	    sb = new StringBuilder();
	    sb.append(i + 1).append("\t");
	    for (int k = 0; k <= w; k++) {
		sb.append(sarr[i][k].v).append(sarr[i][k].used ? "-y" : "-f")
			.append("\t");
	    }
	    System.out.println(sb.toString());
	}
	sb = new StringBuilder();
	int i = items.length - 1;
	int tw = w;
	while (i >= 0 && tw > 0) {
	    if (sarr[i][tw].used) {
		sb.append(i + 1).append("-");
		i -= 1;
		tw -= items[i].w;
	    } else if (i > 0) {
		i -= 1;
	    } else {
		tw--;
	    }
	}
	System.out.println(sb.toString());
	return sarr[items.length - 1][w].v;
    }
}
