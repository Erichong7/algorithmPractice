package midTerm;

public class MyArrayList {

	int maxSize = 5;
	int[] array;
	int currentSize;

	public MyArrayList() {
		array = new int[maxSize];
		currentSize = 0;
	}

	public boolean isFull() {
		return (currentSize == maxSize ? true : false);
	}

	public boolean isEmpty() {
		return (currentSize == 0 ? true : false);
	}

	private void enlarge() {
		maxSize *= 2;
		int[] newArray = new int[maxSize];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	public void addFirst(int value) {
		if (isFull()) {
			enlarge();
		} else {
			for (int i = currentSize; i > 0; i--) {
				array[i] = array[i - 1];
			}
			array[0] = value;
			currentSize++;
		}
	}

	public void addLast(int value) {
		if (isFull()) {
			enlarge();
		} else {
			array[currentSize] = value;
			currentSize++;
		}
	}

	public void add(int index, int value) {
		if (isFull()) {
			enlarge();
		}
		if (index < 0 || index > currentSize) {
			System.out.println("Out Of Index");
		}
		for (int i = currentSize; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = value;
		currentSize++;
	}

	public static void main(String[] args) {

	}

}
