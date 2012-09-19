package huangjingyu.algorithm.common;

import junit.framework.TestCase;

public class HeapTest extends TestCase {	
	
	@Override
	protected void setUp() throws Exception {		
	}


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
