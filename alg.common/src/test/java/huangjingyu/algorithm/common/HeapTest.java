package huangjingyu.algorithm.common;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(5);
        heap.insert(2);
        heap.insert(3);
        assertEquals(2, heap.removeFirst().intValue());
        assertEquals(3, heap.removeFirst().intValue());
        assertEquals(5, heap.removeFirst().intValue());
        assertNull(heap.removeFirst());
    }
}
