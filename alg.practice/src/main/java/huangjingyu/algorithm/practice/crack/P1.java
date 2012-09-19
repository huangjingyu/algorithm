package huangjingyu.algorithm.practice.crack;

import java.util.Comparator;
import java.util.PriorityQueue;

// Median number
public class P1 {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,
	    new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
		    return 0 - o1.compareTo(o2);
		}
	    });
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

    public void addNumber(Integer number) {
	if (maxHeap.peek() == null && minHeap.peek() == null) {
	    maxHeap.offer(number);
	    return;
	}
	if (maxHeap.size() == minHeap.size()) {
	    if (number > minHeap.peek()) {
		maxHeap.offer(minHeap.poll());
		minHeap.offer(number);
	    } else {
		maxHeap.offer(number);
	    }
	} else {
	    if (number < maxHeap.peek()) {
		minHeap.offer(maxHeap.poll());
		maxHeap.offer(number);
	    } else {
		minHeap.offer(number);
	    }
	}
    }

    public Integer getMedian() {
	if (maxHeap.peek() == null)
	    return minHeap.peek();
	if (minHeap.peek() == null)
	    return maxHeap.peek();
	if (maxHeap.size() > minHeap.size())
	    return maxHeap.peek();
	else if (maxHeap.size() == minHeap.size())
	    return (maxHeap.peek() + minHeap.peek()) / 2;
	else
	    return minHeap.peek();
    }

    public void addNumbers(Integer[] arr) {
	for (Integer i : arr) {
	    addNumber(i);
	}
    }

    public static void main(String[] args) {
	P1 t = new P1();
	t.addNumbers(new Integer[] { 3, 9, 2, 8, 4, 6 });
	System.out.println(t.getMedian());
    }
}
