package midTerm;

public class PriorityQueue {

	MyDoubleLinkedList<MyData> list;

	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();

		pq.add(pq.new MyData(3, "ddd"));
		pq.addPriority(pq.new MyData(1, "paaa"));
		pq.add(pq.new MyData(1, "aaa"));
		pq.addPriority(pq.new MyData(5, "pccc"));
		pq.add(pq.new MyData(2, "bbb"));
		pq.add(pq.new MyData(5, "ccc"));
		pq.addPriority(pq.new MyData(2, "pbbb"));
		pq.addPriority(pq.new MyData(3, "pddd"));
		pq.showQueue();

	}

	public PriorityQueue() {
		list = new MyDoubleLinkedList<>();
	}

	public void add(MyData data) {
		list.addLast(data);
	}

	public void addPriority(MyData data) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (data.compareTo(list.get(i)) == -1) {
				list.add(i + 1, data);
				return;
			} else if (i == 0) {
				list.add(i, data);
				return;
			}
		}
	}

	public void showQueue() {
		list.showList();
	}

	public class MyData {
		int priority;
		String name;

		public MyData(int p, String s) {
			priority = p;
			name = s;
		}

		public boolean equals(MyData that) {
			if (this.name == that.name)
				return true;
			else
				return false;
		}

		int compareTo(MyData that) {
			if (this.priority > that.priority)
				return 1;
			else if (this.priority < that.priority)
				return -1;
			else
				return 0;
		}

		public String toString() {
			return name + "(" + priority + ")";
		}

	}
}
