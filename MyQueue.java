package midTerm;

public class MyQueue {

	private int max = 5;
	int[] queue;
	private int front, rear;

	public MyQueue() {
		queue = new int[max];
		front = -1;
		rear = -1;
	}

	private boolean isEmpty() {
		return (front == -1 ? true : false);
	}

	private boolean isFull() {
		if (front == 0 && rear == max - 1) {
			return true;
		}
		if (front == rear + 1) {
			return true;
		}
		return false;
	}

	public void enQueue(int value) {
		if (isFull()) {
			System.out.println("Queue Full!");
		} else {
			if (front == -1)
				front = 0;
			rear = (rear + 1) % max;
			queue[rear] = value;
		}
	}

	public int deQueue() {
		int res;
		if (isEmpty()) {
			System.out.println("Queue Empty!");
			return -1;
		} else {
			res = queue[front];
			if (front == rear) {
				front = -1;
				rear = -1;
			} else {
				front = (front + 1) % max;
			}
		}
		return res;
	}

	public void display() {
		if (isEmpty()) {
			System.out.println("Queue Empty!");
		} else {
			for (int i = front; i != rear; i = (i + 1) % max) {
				System.out.print(queue[i] + " ");
			}
			System.out.print(queue[rear]);
			System.out.println();
		}
	}

	public int peak() {
		return queue[front];
	}

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();

		mq.deQueue(); // Queue empty

		mq.enQueue(1);
		mq.enQueue(2);
		mq.enQueue(3);
		mq.enQueue(4);
		mq.enQueue(5);

		mq.display();

		mq.enQueue(6); // Queue Full

		mq.display();

		int elem = mq.deQueue();

		if (elem != -1) {
			System.out.println("Deleted Element is " + elem);
		}
		mq.enQueue(7);

		mq.display();

		mq.enQueue(8); // Queue Full
	}

}
