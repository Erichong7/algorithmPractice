package midTerm;

public class MyStack {

	int max = 5;
	int[] stack;
	int sp;

	public MyStack() {
		stack = new int[max];
		sp = 0;
	}

	public boolean isEmpty() {
		return (sp == 0 ? true : false);
	}

	public boolean isFull() {
		if (sp == max) {
			return true;
		}
		return false;
	}

	private void enlarge() {
		max *= 2;
		int[] newStack = new int[max];
		for (int i = 0; i < sp; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
	}

	public void push(int value) {
		if (isFull()) {
			enlarge();
		}
		stack[sp] = value;
		sp++;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack Empty");
			return -9999;
		} else {
			return stack[--sp];
		}
	}

	public int peak() {
		return stack[sp - 1];
	}

	public void display() {
		for (int i = 0; i < sp; i++) {
			System.out.print(stack[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MyStack ms = new MyStack();
		ms.push(1);
		ms.push(2);
		ms.push(3);
		ms.push(4);
		ms.push(5);

		ms.display(); // stack 기존max 값만큼 채워넣기

		ms.push(6);
		ms.push(7);
		ms.push(8);

		ms.display(); // max값 초과 --> enlarge

		int poppedValue = ms.pop(); // 마지막으로 들어간 값 pop
		System.out.println("Popped value : " + poppedValue); // pop된 값

		ms.display(); // 8 pop

		System.out.println(ms.peak()); // Stack의 마지막 값 출력
	}

}
