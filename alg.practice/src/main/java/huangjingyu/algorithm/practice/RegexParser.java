package huangjingyu.algorithm.practice;

public class RegexParser implements IRegexParser {

    private static final char EPSILON = 'E';

    @Override
    public String match(String patternString, String candidateString) {
	char[] arr = patternString.toCharArray();
	if (arr.length == 0)
	    return "reject";
	NFA nfa = patternToNFA(arr);
	return match(nfa, candidateString);
    }

    private String match(NFA nfa, String candidateString) {
	char[] arr = candidateString.toCharArray();
	LinkedList<NFANode> states = move(nfa.start, EPSILON);
	int i = 0;
	for (; i < arr.length; i++) {
	    char c = arr[i];
	    states = move(states, c);
	}
	boolean accept = hasFinal(states);
	return accept ? "accept" : "reject";
    }

    private boolean hasFinal(LinkedList<NFANode> list) {
	LinkedNode<NFANode> cur = list.first;
	while (cur != null) {
	    if (cur.value.isFinal)
		return true;
	    cur = cur.next;
	}
	return false;
    }

    private LinkedList<NFANode> move(NFANode nfaNode, char condition) {
	LinkedList<NFANode> list = new LinkedList<NFANode>();
	if (condition == EPSILON) {
	    list.add(nfaNode);
	}
	LinkedNode<NFAEdge> linkedNode = nfaNode.edges.first;
	while (linkedNode != null) {
	    NFAEdge edge = linkedNode.value;
	    if (condition == edge.condition
		    || ('.' == edge.condition && EPSILON != condition)) {
		list.add(edge.dest);
		list.addAll(move(edge.dest, EPSILON));
	    }
	    linkedNode = linkedNode.next;
	}
	return list;
    }

    private LinkedList<NFANode> move(LinkedList<NFANode> preStates,
	    char condition) {
	LinkedList<NFANode> list = new LinkedList<NFANode>();
	LinkedNode<NFANode> cur = preStates.first;
	while (cur != null) {
	    list.addAll(move(cur.value, condition));
	    cur = cur.next;
	}
	return list;
    }

    private NFA patternToNFA(char[] arr) {
	NFA nfa = new NFA();
	nfa.start = nfa.last = new NFANode(true);
	for (char c : arr) {
	    nfa.last.isFinal = false;
	    if (c == '*') {
		NFANode node1 = nfa.last;
		NFANode node2 = new NFANode(false);
		NFANode node3 = new NFANode(true);
		node1.edges.add(new NFAEdge('.', node2));
		node1.edges.add(new NFAEdge(EPSILON, node3));
		node2.edges.add(new NFAEdge(EPSILON, node1));
		node2.edges.add(new NFAEdge(EPSILON, node3));
		nfa.last = node3;
	    } else {
		NFANode node1 = nfa.last;
		NFANode node2 = new NFANode(true);
		node1.edges.add(new NFAEdge(c, node2));
		nfa.last = node2;
	    }
	}
	return nfa;
    }

    static final class LinkedNode<T> {
	LinkedNode<T> next;
	T value;

	public LinkedNode(T value) {
	    this.value = value;
	}
    }

    static final class LinkedList<T> {
	LinkedNode<T> first = null;
	LinkedNode<T> last = null;
	int length = 0;

	public void add(T value) {
	    if (last == null) {
		first = last = new LinkedNode<T>(value);
	    } else {
		last.next = new LinkedNode<T>(value);
		last = last.next;
	    }
	    length++;
	}

	public void addAll(LinkedList<T> list) {
	    LinkedNode<T> cur = list.first;
	    while (cur != null) {
		if (last == null) {
		    first = last = cur;
		} else {
		    last.next = cur;
		    last = last.next;
		}
		cur = cur.next;
	    }
	}
    }

    static final class NFANode {
	boolean isFinal = false;
	LinkedList<NFAEdge> edges = new LinkedList<NFAEdge>();

	public NFANode(boolean isFinal) {
	    this.isFinal = isFinal;
	}
    }

    static final class NFAEdge {
	char condition;
	NFANode dest;

	public NFAEdge() {
	}

	public NFAEdge(char condition, NFANode dest) {
	    this.condition = condition;
	    this.dest = dest;
	}
    }

    static final class NFA {
	NFANode start;
	NFANode last;
    }

}
