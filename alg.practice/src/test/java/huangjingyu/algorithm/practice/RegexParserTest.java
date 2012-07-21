package huangjingyu.algorithm.practice;

import junit.framework.TestCase;

public class RegexParserTest extends TestCase {
    IRegexParser parser = null;

    @Override
    protected void setUp() throws Exception {
	parser = new RegexParser();
    }

    public void test1() {
	String actual = parser.match("abc", "abc");
	assertEquals("accept", actual);
    }

    public void test2() {
	String actual = parser.match("*", "abc");
	assertEquals("accept", actual);
    }

    public void test3() {
	String actual = parser.match("*abc", "abc");
	assertEquals("accept", actual);
    }

    public void test4() {
	String actual = parser.match("*abc", "aaabbbabc");
	assertEquals("accept", actual);
    }

    public void test5() {
	String actual = parser.match("abc", "abc");
	assertEquals("accept", actual);
    }

    public void test6() {
	String actual = parser.match("a*bc", "aaabbbabc");
	assertEquals("accept", actual);
    }

    public void test7() {
	String actual = parser.match("a*bc", "abc");
	assertEquals("accept", actual);
    }

    public void test8() {
	String actual = parser.match("a*", "abc");
	assertEquals("accept", actual);
    }

    public void test9() {
	String actual = parser.match("a*", "a");
	assertEquals("accept", actual);
    }

    public void test10() {
	String actual = parser.match("a*", "aa");
	assertEquals("accept", actual);
    }

    public void test11() {
	String actual = parser.match("a*", "abcdef");
	assertEquals("accept", actual);
    }

    public void test12() {
	String actual = parser.match("*abc*", "abc");
	assertEquals("accept", actual);
    }

    public void test13() {
	String actual = parser.match("*****", "abc");
	assertEquals("accept", actual);
    }
    
    public void test14() {
	String actual = parser.match("...", "abc");
	assertEquals("accept", actual);
    }
    
    public void test15() {
	String actual = parser.match(".*", "abc");
	assertEquals("accept", actual);
    }
    
    public void test16() {
	String actual = parser.match(".bc*", "abc");
	assertEquals("accept", actual);
    }
    
    public void test17() {
	String actual = parser.match(".b*c*a", "abca");
	assertEquals("accept", actual);
    }
    
    public void test18() {
	String actual = parser.match("*", "");
	assertEquals("accept", actual);
    }
    
    public void test51() {
	String actual = parser.match("abc", "abcd");
	assertEquals("reject", actual);
    }
    
    public void test52() {
	String actual = parser.match("*a", "abcd");
	assertEquals("reject", actual);
    }
    
    public void test53() {
	String actual = parser.match("A", "");
	assertEquals("reject", actual);
    }
    
    public void test54() {
	String actual = parser.match(".a*c", "abc");
	assertEquals("reject", actual);
    }
    
    public void test55() {
	String actual = parser.match("a.*b", "abc");
	assertEquals("reject", actual);
    }
    
    public void test56() {
	String actual = parser.match("..", "abc");
	assertEquals("reject", actual);
    }
    
    public void test57() {
	String actual = parser.match("", "");
	assertEquals("reject", actual);
    }
    
    public void test58() {
	String actual = parser.match("", "abc");
	assertEquals("reject", actual);
    }

}
