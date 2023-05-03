package midExam;

public class MyLinkedList1 {
	protected class Node {
		int data;
		Node prev;
		Node next;

		protected Node(int value) {
			data = value;
			prev = null;
			next = null;
		}

		public String toString() {
			return " " + data;
		}
	}

	Node first, last;
	int nOfNodes;

	public MyLinkedList1() {
		first = null;
		last = null;
		nOfNodes = 0;
	}

	public void addFirst(int value) {
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

	public void addLast(int value) {
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

	public void add(int index, int value) {
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

	public int indexOf(int value) {
		Node p = first;
		for (int i = 0; i < nOfNodes; i++) {
			if (p.data == value)
				return i;
			else
				p = p.next;
		}
		return -1; // Not Found
	}

	public int removeFirst() {
		if (first == null)
			return -9999;

		int retValue = first.data;
		first = first.next;

		if (first == null) {
			last = null;
		} else {
			first.prev = null;
		}
		nOfNodes--;
		return retValue;
	}

	public int removeLast() {
		if (last == null)
			return -9999;

		int retValue = last.data;
		last = last.prev;

		if (last == null) {
			first = null;
		} else {
			last.next = null;
		}
		nOfNodes--;
		return retValue;
	}

	public int removeIndex(int index) {
		if (index < 0 || index >= nOfNodes) { // " >= "
			System.out.println("Wrong Index");
			return -9999;
		}
		int retValue;
		if (index == 0)
			return removeFirst();
		else if (index == nOfNodes - 1) // " size - 1 "
			return removeLast();
		else {
			Node p = first;
			for (int i = 0; i < index - 1; i++)
				p = p.next;
			retValue = p.next.data;
			Node temp = p.next.next;
			;
			p.next = temp;
			temp.prev = p;
			nOfNodes--;
		}
		return retValue;
	}

	public int removeValue(int value) {
		int index = indexOf(value);
		removeIndex(index);
		return index;
	}

	public int get(int index) {
		if (index < 0 || index >= nOfNodes) {
			System.out.println("Wrong Index");
			return -9999;
		}

		Node p = first;
		for (int i = 0; i < index - 1; i++)
			p = p.next;
		return p.data;
	}

	public void set(int index, int value) {
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
//		System.out.print("\nList :");
		Node p = first;
		while (p != null) {
			System.out.print(" -> " + p.data + " ");
			p = p.next;
		}
	}

	public static void main(String[] args) {

		int[] data = new int[10];

		for (int i = 0; i < 10; i++)
			data[i] = (int) (Math.random() * 1000); // () 위치! & *1000

		MyLinkedList1 list = new MyLinkedList1();
		System.out.print("\nInitial ");

		for (int i = 0; i < 10; i++) {
			list.addLast(data[i]);
		}
		list.showList();

		System.out.println("\n\n After addFirst(300) : ");
		list.addFirst(300);
		list.showList();

		System.out.println("\n\nAfter add(5, 400) : ");
		list.add(5, 400);
		list.showList();

		int index = list.indexOf(400);
		list.set(index, 450);
		System.out.println("\n\n" + index + "-th Value is changed to : " + list.get(index));
		list.showList();

		System.out.println("\n\nSize is : " + list.size());

		System.out.println("\n\nAfter remove 3-rd element : ");
		list.removeIndex(3); // index =3
		list.showList();
		System.out.println("\n\nAfter remove 450 : ");
		list.removeValue(450); // key value =450;
		list.showList();

		System.out.println("\n\nFirst & Last Values are deleted ");

		list.removeFirst();
		list.removeLast();
		list.showList();
	}

}