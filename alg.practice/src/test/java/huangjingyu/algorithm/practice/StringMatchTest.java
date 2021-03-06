package huangjingyu.algorithm.practice;

import junit.framework.TestCase;
import org.junit.Test;

public class StringMatchTest extends TestCase {

    @Test
    public void test1() {
        StringMatch m = new StringMatch();
        String s = "defabcd";
        String sub = "abc";
        assertEquals(3, m.match(s, sub));
        assertEquals(-1, m.match(s, "abcde"));
        assertEquals(0, m.match(s, "defabcd"));
    }
}
