package huangjingyu.algorithm.practice;

public class Compliment {
	static int getIntegerComplement(int n) {
		int ret = 0;
		int i = 1;
		while (n > 0) {
			int remainder = n % 2;
			ret += (i * (1 - remainder));
			i <<= 1;
			n /= 2;
		}
		return ret;
	}

	public static void main(String[] args) {
		int a = getIntegerComplement(50);
		System.out.println(a);
	}
}
