package huangjingyu.algorithm.practice.crack;

public class P85 {
    public static void printParentheses(int num) {
	printPar(num, num, new char[num * 2], 0);
    }

    public static void printPar(int l, int r, char[] str, int count) {
	if (l < 0 || r < l) {
	    return;
	}
	if (l == 0 && r == 0) {
	    System.out.println(str);
	    return;
	}
	if (l > 0) {
	    str[count] = '(';
	    printPar(l - 1, r, str, count + 1);
	}
	if (l < r) {
	    str[count] = ')';
	    printPar(l, r - 1, str, count + 1);
	}
    }

    public static void main(String[] args) {
	printParentheses(3);
    }
}
