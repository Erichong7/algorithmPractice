package midTerm;

public class MyQueue1 { // size free

	int max = 5;
	int[] queue;
	int front, rear;

	public MyQueue1() {
		queue = new int[max];
		front = -1;
		rear = -1;
	}

	public boolean isFull() {
		if ((front == rear + 1) || (front == 0 && rear == max - 1)) {
			return true;
		}
		return false;
	}

	private void enlarge() {
		max *= 2;
		int[] newQueue = new int[max];
		for (int i = front; i != rear; i = (i + 1) % max) {
			newQueue[i] = queue[i];
		}
		newQueue[rear] = queue[rear];
		queue = newQueue;
	}

	public boolean isEmpty() {
		return (front == -1 ? true : false);
	}

	public void enQueue(int value) {
		if (isFull()) {
			enlarge();
		}
		if (front == -1) {
			front = 0;
		}
		rear = (rear + 1) % max;
		queue[rear] = value;
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

	public static void main(String[] args) {
		MyQueue1 mq = new MyQueue1();

		mq.enQueue(1);
		mq.enQueue(2);
		mq.enQueue(3);
		mq.enQueue(4);
		mq.enQueue(5);

		mq.display(); // max까지 꽉 채운 상태

		mq.enQueue(6);
		mq.enQueue(7);
		mq.enQueue(8);

		mq.display(); // max 사이즈 초과 --> enlarge

		mq.deQueue();
		mq.deQueue();
		mq.deQueue();

		mq.display(); // 1, 2, 3 deQueue

		mq.enQueue(9);
		mq.enQueue(10);

		mq.display(); // 9, 10 enQueue
	}

}
