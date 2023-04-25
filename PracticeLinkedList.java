package midTerm;

public class PracticeLinkedList {

	public class Node {
		int data;
		Node next;

		public Node(int value) {
			data = value;
			next = null;
		}
	}

	Node first;
	int nOfNodes;

	public void addLast(int value) {
		Node p = first;
		if (p == null) {
			first = new Node(value);
			nOfNodes++;
		} else {
			while (p.next != null) {
				p = p.next;
			}
			p.next = new Node(value);
			nOfNodes++;
		}
	}

	public void addFirst(int value) {
		Node newNode = new Node(value);
		newNode.next = first;
		first = newNode;
		nOfNodes++;
	}

	public void add(int index, int value) {
		if (index < 0 || index >= nOfNodes) {
			return;
		}
		if (index == 0) {
			addFirst(value);
		} else {
			Node p = first;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			Node newNode = new Node(value);
			newNode.next = p.next;
			p.next = newNode;
			nOfNodes++;
		}
	}

	public int indexOf(int value) {
		return indexOf(value, first, 0);
	}

	private int indexOf(int value, Node p, int count) {
		if (count >= nOfNodes) {
			return -1;
		}
		if (p.data == value) {
			return count;
		}
		return indexOf(value, p.next, count + 1);
	}

	public void set(int index, int value) {
		if (index < 0 || index >= nOfNodes) {
			return;
		}
		Node p = first;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		p.data = value;
	}

	public int get(int index) {
		if (index < 0 || index >= nOfNodes) {
			return -9999;
		}
		Node p = first;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}

	public int size() {
		return nOfNodes;
	}

	public boolean remove(int index) {
		return remove(index, first, 0);
	}

	private boolean remove(int index, Node p, int count) {
		if (count == index - 1) {
			p.next = p.next.next;
			nOfNodes--;
			return true;
		}
		if (index < 0 || index >= nOfNodes) {
			return false;
		}
		return remove(index, p.next, count + 1);
	}

	public void removeKey(int value) {
		int index = indexOf(value);
		remove(index);
	}

	public void removeFirst() {
		Node p = first;
		first = p.next;
		p.next = p.next.next;
		nOfNodes--;
	}

	public void removeLast() {
		Node p = first;
		while (p.next.next != null) {
			p = p.next;
		}
		p.next = null;
		nOfNodes--;
	}

	public String toString() {
		return toString(first);
	}

	private String toString(Node p) {
		String ret = "";
		if (p == null) {
			return ret;
		}
		ret = String.valueOf(p.data) + " ";
		ret += toString(p.next);
		p = p.next;
		return ret;
	}

	public static void main(String[] args) {
		PracticeLinkedList list = new PracticeLinkedList();

		for (int i = 0; i < 10; i++) {
			list.addLast(i);
		}
		System.out.println("AddLast 0 ~ 9");
		System.out.println(list.toString());

		list.addFirst(300);
		System.out.println("AddFirst 300");
		System.out.println(list.toString());

		list.add(5, 400); // 숙제 i번째부터 밀어넣으면 됨
		System.out.println("Add Value 400 to Index 5");
		System.out.println(list.toString());

		int index = list.indexOf(400);
		System.out.println("< Index Of 400 >");
		System.out.println(index);
		System.out.println();

		list.set(index, 450);
		System.out.println("Set Index 5 to 450");
		System.out.println(list.toString());

		System.out.println("Get Value of Index 5");
		System.out.println(list.get(index));
		System.out.println();

		System.out.println("< Size >");
		System.out.println(list.size());
		System.out.println();

		list.remove(3); // index = 3
		System.out.println("Remove index 3");
		System.out.println(list.toString());

		list.removeKey(450); // key value = 450
		System.out.println("Remove 450");
		System.out.println(list.toString());

		list.removeFirst();
		System.out.println("Remove First");
		System.out.println(list.toString());

		list.removeLast();
		System.out.println("Remove Last");
		System.out.println(list.toString());

		System.out.println("< Size >");
		System.out.println(list.size());
	}

}
