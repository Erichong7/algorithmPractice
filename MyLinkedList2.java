package midExam;

public class MyLinkedList2<T> {
	protected class Node<T> {
		T data;
		Node prev;
		Node next;

		protected Node(T value) {
			data = value;
			prev = null;
			next = null;
		}

		public String toString() {
			return " " + data.toString();
		}
	}

	Node first, last;
	int nOfNodes;

	public MyLinkedList2() {
		first = null;
		last = null;
		nOfNodes = 0;
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

	public void add(int index, T value) {
		if (index < 0 || index > nOfNodes) {
			System.out.println("Wrong Index");
			return;
		}
		if (index == 0)
			addFirst(value);
		else if (index == nOfNodes)
			addLast(value);
		else {
			Node newNode = new Node(value);
			Node p = first;
			for (int i = 0; i < index - 1; i++)
				p = p.next;
			newNode.prev = p;
			newNode.next = p.next;
			p.next = newNode;
			p.next.prev = newNode;
			nOfNodes++;
		}
	}

	public int indexOf(T value) {
		Node p = first;
		for (int i = 0; i < nOfNodes; i++) {
			if (p.data.equals(value))
				return i;
			else
				p = p.next;
		}
		return -1; // Not Found
	}

	public T removeFirst() {
		if (first == null)
			return null;
		T retValue = (T) first.data;
		first = first.next;

		if (first == null) {
			last = null;
		} else {
			first.prev = null;
		}
		nOfNodes--;
		return retValue;
	}

	public T removeLast() {
		if (last == null)
			return null;
		T retValue = (T) last.data;
		last = last.prev;

		if (last == null) {
			first = null;
		} else {
			last.next = null;
		}
		nOfNodes--;
		return retValue;
	}

	public T removeIndex(int index) {
		if (index < 0 || index >= nOfNodes) { // " >= "
			System.out.println("Wrong Index");
			return null;
		}
		T retValue;
		if (index == 0)
			return removeFirst();
		else if (index == nOfNodes - 1) // " size - 1 "
			return removeLast();
		else {
			Node p = first;
			for (int i = 0; i < index - 1; i++)
				p = p.next;
			retValue = (T) p.next.data;
			Node temp = p.next.next;
			;
			p.next = temp;
			temp.prev = p;
			nOfNodes--;
		}
		return retValue;
	}

	public int removeValue(T value) {
		int index = indexOf(value);
		removeIndex(index);
		return index;
	}

	public T get(int index) {
		if (index < 0 || index >= nOfNodes) {
			System.out.println("Wrong Index");
			return null;
		}

		Node p = first;
		for (int i = 0; i < index - 1; i++)
			p = p.next;
		return (T) p.data;
	}

	public void set(int index, T value) {
		if (index < 0 || index >= nOfNodes) {
			System.out.println("Wrong Index");
			return;
		}
		Node p = first;
		for (int i = 0; i < index; i++)
			p = p.next;
		p.data = value;
	}

	public int size() {

		return nOfNodes;
	}

	public void showList() {
		Node p = first;
		while (p != null) {
			System.out.print(" -> " + p.data.toString() + " ");
			p = p.next;
		}
	}
}
