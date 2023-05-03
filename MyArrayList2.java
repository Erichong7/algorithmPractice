package midExam;

public class MyArrayList2<T> {
	int maxSize = 10;
	T[] array;
	int currentSize;

	@SuppressWarnings("unchecked")
	public MyArrayList2() {
		array = (T[]) (new Object[maxSize]);
		currentSize = 0;
	}

	private boolean isFull() {
		if (currentSize == maxSize) {
			return true;
		} else
			return false;
	}

	private void enlarge() {
		System.out.println("\nList Capacity is enlarged !");
		@SuppressWarnings("unchecked")
		T[] tempArray = (T[]) (new Object[maxSize * 2]);
		for (int i = 0; i < currentSize; i++)
			tempArray[i] = array[i];
		maxSize *= 2;
		array = tempArray;
	}

	public void addLast(T value) {
		if (isFull()) {
			enlarge();
		}
		array[currentSize++] = value;
	}

	public void addFirst(T value) {
		add(0, value);
	}

	public void add(int index, T value) {
		if (isFull()) {
			enlarge();
		}
		for (int i = currentSize; i > index; i--)
			array[i] = array[i - 1];
		array[index] = value;
		currentSize++;
	}

	public boolean isEmpty() {
		if (currentSize == 0) {
			return true;
		} else
			return false;
	}

	public T removeLast() {
		if (isEmpty()) {
			System.out.println("List Empty!");
			return null;
		} else {
			return array[--currentSize];
		}
	}

	public T removeFirst() {
		if (isEmpty()) {
			System.out.println("List Empty!");
			return null;
		} else {
			T retValue = array[0];
			currentSize--;
			for (int i = 0; i < currentSize; i++)
				array[i] = array[i + 1];
			return retValue;
		}
	}

	public int removeValue(T value) {
		int index = indexOf(value);
		if (index == -1) {
			System.out.println("Not Found!");
			return -1;
		} else {
			removeIndex(index);
			return index;
		}
	}

	public T removeIndex(int index) {
		if (index >= currentSize || index < 0) {
			System.out.println("Wrong index!");
			return null;
		} else {
			T retValue = array[index];
			currentSize--;
			for (int i = index; i < currentSize; i++)
				array[i] = array[i + 1];
			return retValue;
		}
	}

	public int size() {
		return currentSize;
	}

	public T get(int index) {
		if (index < 0 || index >= currentSize) {
			System.out.println("\nWrong Index!");
			return null;
		} else
			return array[index];
	}

	public void set(int index, T value) {
		array[index] = value;
	}

	public int indexOf(T value) {
		for (int i = 0; i < currentSize; i++) {
			if (value.equals(array[i]))
				return i;
		}
		return -1;
	}

	public void showList() {
		for (int i = 0; i < currentSize; i++)
			System.out.print(array[i].toString() + "  ");
	}
}
