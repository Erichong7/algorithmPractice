package midTerm;

public class MyDoubleLinkedList<T> {

	private class Node {
		T data;
		Node prev;
		Node next;

		public Node(T value) {
			data = value;
			prev = null;
			next = null;
		}
	}

	private Node first;
	private Node last;
	private int nOfNodes;

	public MyDoubleLinkedList() {
		first = null;
		last = null;
		nOfNodes = 0;
	}

	public void addLast(T value) {
		Node newNode = new Node(value);
		if (last == null) {
			first = newNode;
			last = newNode;
		} else {
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
		}
		nOfNodes++;
	}

	public void addFirst(T value) {
		Node newNode = new Node(value);
		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		nOfNodes++;
	}

	public void add(int index, T value) {
		if (index < 0 || index > nOfNodes) {
			System.out.println("Out Of Index!");
			return;
		}
		if (index == 0) {
			addFirst(value);
		} else if (index == nOfNodes) {
			addLast(value);
		} else {
			Node p = first;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			Node newNode = new Node(value);
			newNode.prev = p;
			newNode.next = p.next;
			p.next.prev = newNode;
			p.next = newNode;
			nOfNodes++;
		}
	}

	public void set(int index, T value) {
		if (index < 0 || index > nOfNodes) {
			System.out.println("Out Of Index!");
			return;
		} else {
			Node p = first;
			for (int i = 0; i < index; i++) {
				p = p.next;
			}
			p.data = value;
		}
	}

	public T get(int index) {
		if (index < 0 || index > nOfNodes) {
			System.out.println("Out Of Index!");
			return null;
		} else {
			Node p = first;
			for (int i = 0; i < index; i++) {
				p = p.next;
			}
			return p.data;
		}
	}

	public void removeFirst() {
		if (first == null) {
			System.out.println("List Empty");
		} else {
			first = first.next;
			first.prev = null;
			nOfNodes--;
		}
	}

	public void removeLast() {
		if (last == null) {
			System.out.println("List Empty");
		} else {
			last = last.prev;
			last.next = null; // 중요!
			nOfNodes--;
		}
	}

	public void remove(int index) {
		if (index < 0 || index > nOfNodes) {
			System.out.println("Out Of Index!");
			return;
		} else if (index == 0) {
			removeFirst();
		} else if (index == nOfNodes - 1) {
			removeLast();
		} else {
			Node p = first;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			p.next = p.next.next;
			p.next.prev = p;
			nOfNodes--;
		}
	}

	public void removeKey(T value) {
		int index = indexOf(value);
		remove(index);
	}

	public int size() {
		return nOfNodes;
	}

	public int indexOf(T value) {
		Node p = first;
		for (int i = 0; i < nOfNodes; i++) {
			if (p.data.equals(value)) {
				return i;
			} else {
				p = p.next;
			}
		}
		return -1;
	}

	public void showList() {
		if (first == null) {
			System.out.println("List Empty!");
		} else {
			Node p = first;
			while (p != null) {
				System.out.print(p.data + " ");
				p = p.next;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<>();

		for (int i = 0; i < 10; i++) {
			list.addLast(i);
		}
		list.showList();
		list.addFirst(300);
		list.showList();
		list.add(11, 400); // 숙제 i번째부터 밀어넣으면 됨
		list.showList();

		int index = list.indexOf(400);
		System.out.println(index);
		list.set(index, 450);
		list.showList();
		System.out.println(list.get(index));
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
