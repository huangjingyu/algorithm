package huangjingyu.algorithm.practice;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<String> permute(String s) {
	int len = s.length();
	int total = 2 << len - 1;
	int[] arr = new int[len];
	for (int i = 0; i < len; i++) {
	    arr[i] = 1 << i;
	}
	List<String> list = new ArrayList<String>();
	StringBuilder sb = new StringBuilder();
	for (int i = 1; i <= total; i++) {
	    sb.delete(0, sb.length());
	    for (int k = 0; k < len; k++) {
		if ((i & arr[k]) != 0) {
		    sb.append(s.charAt(k));
		}
	    }
	    list.add(sb.toString());
	}
	return list;
    }

    public static void main(String[] args) {
	Permutation t = new Permutation();
	List<String> list = t.permute("abc");
	for (String s : list) {
	    System.out.println(s);
	}
    }
}
