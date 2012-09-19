package huangjingyu.algorithm.practice.crack;

public class P19_4 {
    public static int getMax(int a, int b) {
	int c = a - b;
	int k = (c >> 31) & 0x1;
	return a - k * c;
    }

    public static void main(String[] args) {
	System.out.println(getMax(3, 7));
	System.out.println(getMax(10, 7));
    }
}
