package midExam;

public class MidExam2023 {

	// no.1 : Num & namd,
	// in. num. : name : 60221344 홍정우

	// no.2 : change MyStack with MyArrayList2 for Record type data
	static class MyStack {
		MyArrayList2<String> stack;

		public int stackPointer() {
			return stack.size();
		}

		public MyStack() {
			stack = new MyArrayList2<>();
		}

		public void push(String d) {
			stack.addLast(d);
		}

		public String pop() {
			if (isEmpty()) {
				System.out.println("Stack Empty");
				return null;
			} else
				return stack.removeIndex(stack.size() - 1);
		}

		public boolean isEmpty() {
			return stack.isEmpty();
		}

		public String peek() {
			return stack.get(stack.size() - 1);
		}

		public void showStack() {
			stack.showList();
		}
	}

	// no.3 : change MyQueue with MyLinkedList2 for Record type data
	static class MyQueue {
		MyLinkedList2<String> queue;

		public MyQueue() {
			queue = new MyLinkedList2<>();
		}

		public String front() {
			return queue.get(0);
		}

		public String rear() {
			return queue.get(queue.size());
		}

		public void enqueue(String value) { // add
			queue.addLast(value);
		}

		public String dequeue() {
			if (isEmpty()) {
				System.out.println("Queue Empty");
				return null;
			} else {
				return queue.removeFirst();
			}
		}

		public boolean isEmpty() {
			return queue.size() == 0;
		}

		public String peek() {
			if (isEmpty()) {
				System.out.println("Queue Empty");
				return null;
			} else {
				return queue.get(0);
			}
		}

		public int size() {
			return queue.size();
		}

		public void showQueue() {
			queue.showList();
		}
	}

	// no.4 : make a method "sortNAdd" into MyLinkeListS
	static class MyLinkedListS extends MyLinkedList1 {

		public void sortedAdd(int value) {
			Node newNode = new Node(value);
			if (first == null || first.data > value) {
				addFirst(value);
			} else {
				Node temp = first;
				while (temp != null) {
					if (temp.data < value)
						temp = temp.next;
					else {// insert value before temp
						newNode.prev = temp.prev;
						newNode.next = temp;
						temp.prev.next = newNode;
						temp.prev = newNode;
						nOfNodes++;
						return;
					}
				}
				addLast(value);
			}
		}

		public int indexOf(int value) {
			return indexOf(first, value, -1);
		}

		private int indexOf(Node f, int value, int index) {
			if (f == null || f.data > value)
				return index;
			else if (f.data == value) {
				return ++index;
			} else
				return indexOf(f.next, value, ++index);
		}
	}

	// no.5 : scheduler
	static class Scheduler {
		private class Element {
			int date;
			MyLinkedList2<String> taskList;

			private Element(int d) {
				date = d;
				taskList = new MyLinkedList2<>();
			}
		}

		MyArrayList2<Element> table;

		public Scheduler() {
			table = new MyArrayList2<>();
		}

		private int getIndex(int d) {
			for (int i = 0; i < table.size(); i++) {
				if (table.get(i).date == d)
					return i;
			}
			return -1;
		}

		public void addTask(int d, String t) {
			int index = getIndex(d);
			if (index == -1) {
				table.addLast(new Element(d));
				index = table.size() - 1;
			}
			table.get(index).taskList.addFirst(t);
		}

		public void freeTask(int d, String t) {
			int index = getIndex(d);
			int res;
			if (index == -1) {
				System.out.println("No Schedule to be deleted");
				return;
			} else {
				res = table.get(index).taskList.removeValue(t);
				if (res == -1) {
					System.out.println("No Schedule to be deleted");
					return;
				}
				if (table.get(index).taskList.size() == 0)
					table.removeIndex(index);
			}
		}

		public void showSchedule() {
			for (int i = 0; i < table.size(); i++) {
				System.out.print("\n[" + table.get(i).date + "] ");

				table.get(i).taskList.showList();
			}
		}

	}

	public static void main(String[] args) {

		// no. 1 : id. num & name !

		// no.2 : new MyStack for Record using MyArrayList2
		System.out.println("\n[2] Stack ==================");
		MyStack s = new MyStack();
		System.out.println("\nInitial Stack Status : Empty is " + s.isEmpty());
		s.push("aaa");
		s.push("bbb");
		s.push("ccc");
		System.out.print("After 3 pushes : ");
		s.showStack();
		System.out.println("\nPeek Test : " + s.peek() + " >> The size is " + s.stackPointer());
		System.out.println("Pop Test : " + s.pop() + " >> The size is" + s.stackPointer());
		System.out.print("Stack is : ");
		s.showStack();

		// no. 3 : new MyQueue for Record using MyArrayList2
		System.out.println("\n\n[3] Queue ==================");
		MyQueue q = new MyQueue();
		System.out.println("Initial Queue Status : Empty is " + q.isEmpty());
		q.enqueue("aaa");
		q.enqueue("bbb");
		q.enqueue("ccc");
		System.out.print("After 3 enqueues : ");
		q.showQueue();
		System.out.println("\nPeek Test : " + q.peek() + " >> The size is " + q.size());
		System.out.println("Dequeue Test : " + q.dequeue() + " >> The size is " + q.size());
		System.out.print("Queue is : ");
		q.showQueue();
		System.out.println();

		// no. 4 : Sorted List
		System.out.println("\n[4] Sorted List ==================");
		MyLinkedListS sl = new MyLinkedListS();
		sl.sortedAdd(4);
		sl.sortedAdd(1);
		sl.sortedAdd(5);
		sl.sortedAdd(3);
		sl.sortedAdd(8);
		sl.showList();
		System.out.println();
		System.out.println("Index of 3 : " + sl.indexOf(3));
		System.out.println("Index of 8 : " + sl.indexOf(8));
		System.out.println("Index of 1 : " + sl.indexOf(1));

		// no. 5 : Scheduler
		System.out.println("\n[5] Scheduler ==================\n");
		Scheduler ms = new Scheduler();

		ms.addTask(2, "Reading");
		ms.addTask(13, "Coding");
		ms.addTask(5, "Soccer");
		ms.addTask(2, "swim");
		ms.addTask(5, "writing");
		ms.addTask(2, "party");
		System.out.println("<After 6 adds> ");
		ms.showSchedule();
		ms.freeTask(2, "Reading");
		System.out.println("\n\n<After 1 free> ");
		ms.showSchedule();
		ms.freeTask(13, "Coding");
		System.out.println("\n\n<After 1 more free>");
		ms.showSchedule();

		System.out.println("\nEnd ==================");
	}
}
