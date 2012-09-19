package huangjingyu.algorithm.practice.crack;

public class P51 {
    public static int updateBits(int n, int m, int i, int j) {
	int t = (1 << (j + 1)) - (1 << i);
	t = ~t;
	return (n & t) | (m << i);
    }

    public static void main(String[] args) {
	int n = Integer.parseInt("10000000000", 2);
	int m = Integer.parseInt("10101", 2);
	int i = 2;
	int j = 6;
	int ret = updateBits(n, m, i, j);
	System.out.println(Integer.toBinaryString(ret));
    }
}
