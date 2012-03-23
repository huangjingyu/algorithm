package huangjingyu.algorithm.common;

import java.util.Comparator;

/**
 * 
 *  1. A node is either red or black.
 *  2. The root is black. (This rule is sometimes omitted from other definitions. 
 *     Since the root can always be changed from red to black, but not necessarily vice-versa, this rule has little effect on analysis.)
 *  3. All leaves are the same color as the root.
 *  4. Both children of every red node are black.
 *  5. Every simple path from a given node to any of its descendant leaves contains the same number of black nodes.
 *
 */
public class RBTree<K, V> {
	public static final int RED = 1;
	public static final int BLACK = 2;

	private Node root;
	private final Comparator<K> comparator;

	public RBTree() {
		comparator = null;
	}

	public RBTree(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	public void insert(K key, V item) {
		if (root == null) {
			setRoot(new Node(key, item, BLACK));
			return;
		}
		Node node = root;
		main: while (true) {
			int cmp = compare(key, node.getKey());
			if (cmp == 0) {
				node.setKey(key);
				node.setItem(item);
				break main;
			} else if (cmp < 0) {
				if (node.getLeft() == null) {
					node.setLeft(new Node(key, item, RED));
					checkInsertRule(node.getLeft());
					break main;
				} else {
					node = node.getLeft();
				}
			} else {
				if (node.getRight() == null) {
					node.setRight(new Node(key, item, RED));
					checkInsertRule(node.getRight());
					break main;
				} else {
					node = node.getRight();
				}
			}
		}
	}

	private void checkInsertRule(Node node) {
		Node n = node;
		main: while (n != null) {
			if (n.getParent() == null) {
				n.setColor(BLACK);
				return;
			}
			if (n.getParent().getColor() == BLACK)
				return;
			Node g = grandParent(n);
			Node u = uncle(n);
			if (u != null && u.color == RED) {
				n.getParent().setColor(BLACK);
				u.setColor(BLACK);
				g.setColor(RED);
				n = g;
				continue main;
			}
			if (n == n.getParent().getRight() && n.getParent() == g.getLeft()) {
				rotateLeft(n.getParent());
				n = n.getLeft();
			} else if (n == n.getParent().getLeft() && n.getParent() == g.getRight()) {
				rotateRight(n.getParent());
				n = n.getRight();
			}
			n.getParent().setColor(BLACK);
			g.setColor(RED);
			if (n == n.getParent().getLeft()) {
				rotateRight(g);
				return;
			} else {
				rotateLeft(g);
				return;
			}
		}
	}

	private void rotateLeft(Node node) {
		Node rChild = node.getRight();
		if (node.getParent() == null) {
			rChild.setParent(null);
			setRoot(rChild);
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(rChild);
		} else {
			node.getParent().setRight(rChild);
		}
		node.setRight(rChild.getLeft());
		rChild.setLeft(node);
	}

	private void rotateRight(Node node) {
		Node lChild = node.getLeft();
		if (node.getParent() == null) {
			lChild.setParent(null);
			setRoot(lChild);
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(lChild);
		} else {
			node.getParent().setRight(lChild);
		}
		node.setLeft(lChild.getRight());
		lChild.setRight(node);
	}

	private Node grandParent(Node node) {
		if (node.getParent() != null)
			return node.getParent().getParent();
		return null;
	}

	private Node uncle(Node node) {
		Node gp = grandParent(node);
		if (gp == null)
			return null;
		if (node.getParent() == gp.getLeft())
			return gp.getRight();
		else
			return gp.getLeft();
	}

	private Node sibling(Node node, Node parent) {
		if (parent == null)
			return null;
		if (node == parent.getLeft())
			return parent.getRight();
		else
			return parent.getLeft();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int compare(K k1, K k2) {
		return comparator == null ? ((Comparable) k1).compareTo(k2) : comparator.compare(k1, k2);
	}

	public void delete(K key) {
		Node node = searchNode(key);
		if (node == null)
			return;
		if (node.getLeft() != null && node.getRight() != null) {
			Node leftMax = leftMax(node);
			node.setKey(leftMax.getKey());
			node.setItem(leftMax.getItem());
			node = leftMax;
		}
		if (node.getColor() == RED) {
			replace(node, null);
			return;
		}
		Node p = node.getParent();
		Node s = sibling(node, p);
		Node c = node.getLeft() == null ? node.getRight() : node.getLeft();
		replace(node, c);
		if (c != null && c.getColor() == RED) {
			c.setColor(BLACK);
			return;
		}
		node = c;

		main: while (true) {
			if (p == null)
				return;
			if (s.getColor() == RED) {
				p.setColor(RED);
				s.setColor(BLACK);
				if (s == p.getLeft())
					rotateRight(p);
				else
					rotateLeft(p);

				s = sibling(node, p);
			}
			if (p.getColor() == BLACK && s.getColor() == BLACK
					&& (s.getLeft() == null || s.getLeft().getColor() == BLACK)
					&& (s.getRight() == null || s.getRight().getColor() == BLACK)) {
				s.setColor(RED);
				node = p;
				p = node.getParent();
				s = sibling(node, p);
				continue main;
			}
			if (p.getColor() == RED && s.getColor() == BLACK
					&& (s.getLeft() == null || s.getLeft().getColor() == BLACK)
					&& (s.getRight() == null || s.getRight().getColor() == BLACK)) {
				s.setColor(RED);
				p.setColor(BLACK);
				return;
			}
			Node sl = s.getLeft();
			Node sr = s.getRight();
			//If SL/SR, one Red, One Black
			if (node == p.getLeft() && (sl != null && sl.getColor() == RED) && (sr == null || sr.getColor() == BLACK)) {
				s.setColor(RED);
				sl.setColor(BLACK);
				rotateRight(s);
			} else if (node == p.getRight() && (sr != null && sr.getColor() == RED)
					&& (sl == null || sl.getColor() == BLACK)) {
				s.setColor(RED);
				sr.setColor(BLACK);
				rotateLeft(s);
			}
			s = sibling(node, p);
			sl = s.getLeft();
			sr = s.getRight();

			s.setColor(p.getColor());
			p.setColor(BLACK);
			if (node == p.getLeft()) {
				sr.setColor(BLACK);
				rotateLeft(p);
			} else {
				sl.setColor(BLACK);
				rotateRight(p);
			}
			return;
		}
	}

	private void replace(Node node, Node newNode) {
		Node p = node.getParent();
		if (p == null) {
			if (newNode != null) {
				newNode.setColor(BLACK);
			}
			setRoot(newNode);
		} else if (node == p.getLeft()) {
			p.setLeft(newNode);
		} else {
			p.setRight(newNode);
		}
	}

	private Node leftMax(Node node) {
		Node n = node.getLeft();
		while (n.getRight() != null) {
			n = n.getRight();
		}
		return n;
	}

	public V search(K key) {
		Node node = searchNode(key);
		return node == null ? null : node.getItem();
	}

	private Node searchNode(K key) {
		Node node = root;
		while (node != null) {
			int cmp = compare(key, node.getKey());
			if (cmp == 0)
				return node;
			if (cmp > 0)
				node = node.getRight();
			else
				node = node.getLeft();
		}
		return null;
	}

	private void setRoot(Node root) {
		if (root != null)
			root.setParent(null);
		this.root = root;
	}

	private class Node {
		public Node(K key, V item, int color) {
			this.key = key;
			this.item = item;
			this.color = color;
		}

		private K key;
		private V item;
		private int color;
		private Node parent;
		private Node left;
		private Node right;

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getItem() {
			return item;
		}

		public void setItem(V item) {
			this.item = item;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
			if (left != null) {
				left.setParent(this);
			}
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
			if (right != null) {
				right.setParent(this);
			}
		}

	}
}
