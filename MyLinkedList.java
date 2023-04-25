package midTerm;

public class MyLinkedList<T> {

	public class Node { // LinkedList의 앞점
		T data;
		Node next;

		public Node(T value) {
			data = value;
			next = null;
		}
	}

	Node first;
	int nOfNodes;

	public MyLinkedList() {
		first = null;
		nOfNodes = 0;
	}

	public boolean isEmpty() {
		if (nOfNodes == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addFirst(T value) {
		Node newNode = new Node(value);
		newNode.next = first;
		first = newNode;
		nOfNodes++;
	}

	public void addLast(T value) {
		Node p = first;
		if (p == null) {
			first = new Node(value);
			nOfNodes++;
		} else {
			while (p.next != null) {
				p = p.next;
			}
			// p <== last Node
			p.next = new Node(value);
			nOfNodes++;
		}
	}

	public void add(int index, T value) {
		if (index < 0 || index > nOfNodes) {
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

	public int size() {
		return nOfNodes;
	}

	public T get(int index) {
		if (index < 0 || index >= nOfNodes) {
			return null;
		}
		Node p = first;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}

	public int indexOf(T value) {
		Node p = first;
		for (int i = 0; i < nOfNodes; i++) {
			if (p.data == value) {
				return i;
			} else {
				p = p.next;
			}
		}
		return -1;
	}

	public void set(int index, T value) {
		if (index < 0 || index >= nOfNodes) {
			return;
		}
		Node p = first;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		p.data = value;
	}

	public void remove(int index) {
		if (index < 0 || index >= nOfNodes) {
			return;
		}
		if (index == 0) {
			removeFirst();
		} else {
			Node p = first;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			p.next = p.next.next;
			nOfNodes--;
		}
		return;
	}

	public void removeKey(int value) {
		nOfNodes--;
	}

	private void removeFirst() {
		if (nOfNodes > 0) {
			first = first.next;
			nOfNodes--;
		}
	}

	private void removeLast() {
		remove(nOfNodes - 1);
	}

	public void showList() {
		Node p = first;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		for (int i = 0; i < 20; i++) {
			list.addLast(i);
		}
		list.showList();
		list.addFirst(300);
		list.showList();
		list.add(5, 400); // 숙제 i번째부터 밀어넣으면 됨
		list.showList();

		int index = list.indexOf(400);
		System.out.println(index);
		list.set(index, 450);
		list.showList();
		list.get(index);
		list.showList();
		System.out.println(list.size());

		list.remove(3); // index = 3
		list.showList();
		list.removeKey(450); // key value = 450
		list.showList();
		list.removeFirst();
		list.showList();
		list.removeLast();
		list.showList();
	}

}
