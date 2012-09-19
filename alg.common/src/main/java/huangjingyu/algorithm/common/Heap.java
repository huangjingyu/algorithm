package huangjingyu.algorithm.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Heap<T extends Comparable<T>> {
	private boolean leastFirst;
	private boolean fixedSize;
	private int size = -1;
	private Comparator<T> comparator;
	private ArrayList<T> heap;

	public Heap() {
		this(true, false, 16);
	}

	public Heap(int initialSize) {
		this(true, false, initialSize);
	}

	public Heap(boolean leastFirst, boolean fixedSize, int initialSize) {
		this.leastFirst = leastFirst;
		this.fixedSize = fixedSize;
		this.size = initialSize;
		heap = new ArrayList<T>(this.size);
		if (leastFirst) {
			comparator = new Comparator<T>() {
				@Override
				public int compare(T t0, T t1) {
					return 0 - t0.compareTo(t1);
				}
			};
		} else {
			comparator = new Comparator<T>() {
				@Override
				public int compare(T t0, T t1) {
					return t0.compareTo(t1);
				}
			};
		}
	}

	public void insert(T obj) {
		heap.add(obj);
		if (leastFirst) {
			bottomUpSwap(heap.size() - 1);
		}
	}

	public T getFirst() {
		return heap.get(0);
	}

	public T removeFirst() {
		if (heap.size() == 0)
			return null;
		T obj = heap.get(0);
		Collections.swap(heap, 0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		topDownSwap(0);
		return obj;
	}

	private void bottomUpSwap(int i) {
		i += 1;
		main: while (i > 1) {
			T parent = heap.get(i / 2 - 1);
			T cur = heap.get(i - 1);
			if (comparator.compare(parent, cur) < 0) {
				Collections.swap(heap, i / 2 - 1, i - 1);
				i /= 2;
			} else {
				break main;
			}
		}
	}

	private void topDownSwap(int i) {
		i += 1;
		main: while (2 * i < heap.size()) {
			T parent = heap.get(i - 1);
			T lChild = heap.get(2 * i - 1);
			T rChild = heap.get(2 * i);
			if (comparator.compare(lChild, rChild) >= 0) {
				if (comparator.compare(parent, lChild) >= 0) {
					break main;
				} else {
					Collections.swap(heap, i - 1, 2 * i - 1);
					i = 2 * i - 1;
				}
			} else {
				if (comparator.compare(parent, rChild) >= 0) {
					break main;
				} else {
					Collections.swap(heap, i - 1, 2 * i);
					i = 2 * i;
				}
			}
		}
	}
}
