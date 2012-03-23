package huangjingyu.algorithm.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import junit.framework.TestCase;

public class RBTreeTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}

	public void test() {
		RBTree<String, String> t = new RBTree<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 50; i++) {
			map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			t.insert(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			assertEquals(entry.getValue(), t.search(entry.getKey()));
		}
		List<String> keyList = new ArrayList<String>(map.keySet());
		for (String key : keyList) {
			t.delete(key);
			assertNull(t.search(key));
			map.remove(key);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				assertEquals(entry.getValue(), t.search(entry.getKey()));
			}
		}
	}
}
