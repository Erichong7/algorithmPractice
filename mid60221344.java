package midExam;

public class mid60221344 {

	// no.1 : Num & namd,
	// in. num. : 60221344 name : 홍정우

	// no.2 : Memory Manager

	static class Page {
		Object o;
		int nextPage;

		Page(int next) { // init
			nextPage = next;
		}
	}

	static int memorySize;
	static Page[] memory;

	static class MemoryManager {
		int headOfFreeList;
		int freeNOfPages;

		public MemoryManager(int size) { // init
			// fill here
			Page page;
			memorySize = size;
			memory = new Page[memorySize];
			headOfFreeList = 0;
			freeNOfPages = memorySize;
			for (int i = 0; i < memorySize; i++) {
				if (i == memorySize - 1) {
					page = new Page(-1);
				} else {
					page = new Page(i + 1);
				}
				memory[i] = page;
			}
		}

		public int allocate(int nOfPages) {
			// fill here
			if (freeNOfPages < nOfPages) {
				return -1;
			}

			int res = headOfFreeList;
			int p = headOfFreeList;
			for (int i = 0; i < nOfPages - 1; i++) {
				freeNOfPages--;
				p = memory[p].nextPage;
			}
			freeNOfPages--;
			headOfFreeList = memory[p].nextPage;
			memory[p].nextPage = -1;
			return res;
		}

		public void free(int headOfReturnedMemory) {
			// fill here
			int p = headOfReturnedMemory;
			while (memory[p].nextPage != -1) {
				freeNOfPages++;
				p = memory[p].nextPage;
			}
			freeNOfPages++;
			memory[p].nextPage = headOfFreeList;
			headOfFreeList = headOfReturnedMemory;
		}

		public void showFreeMemory() {
			System.out.println("\n[ Free Memory Status ]");
			System.out.println("*** Number of Free Memory Blocks : " + freeNOfPages);
			System.out.print("*** List of Free Pages : " + headOfFreeList);
			if (headOfFreeList != -1) {
				int p = headOfFreeList;
				while (memory[p].nextPage != -1) {
					p = memory[p].nextPage;
					System.out.print(" -> " + p);
				}
			}
		}
	}

	static class Process {
		int headOfMyMemory;
		int numberOfMyMemory;

		Process() { // init
			headOfMyMemory = -1;
			numberOfMyMemory = 0;
		}

		public int numberOfMyMemory() {
			return (1 + (int) (3 * Math.random()));
		}

		public int computeMemoryRequred() {
			return (1 + (int) (3 * Math.random()));
		}

		public void getMemory(int headOfAllocatedList) {
			// fill here
			int p = headOfAllocatedList;
			while (memory[p].nextPage != -1) {
				numberOfMyMemory++;
				p = memory[p].nextPage;
			}
			numberOfMyMemory++;
			memory[p].nextPage = headOfMyMemory;
			headOfMyMemory = headOfAllocatedList;
		}

		public int returnMemory() {
			int numberOfReturned = (1 + (int) (numberOfMyMemory * Math.random()));
			int headOfReturnedList;
			// fill here
			headOfReturnedList = headOfMyMemory;
			int p = headOfReturnedList;
			for (int i = 0; i < numberOfReturned - 1; i++) {
				p = memory[p].nextPage;
				numberOfMyMemory--;
			}
			numberOfMyMemory--;
			headOfMyMemory = memory[p].nextPage;
			memory[p].nextPage = -1;

			return headOfReturnedList;
		}

		public int size() {
			return numberOfMyMemory;
		}

		public void showProcessMemory() {
			System.out.print("*** List of My Pages [" + numberOfMyMemory + "] : " + headOfMyMemory);
			if (headOfMyMemory != -1) {
				int p = headOfMyMemory;
				while (memory[p].nextPage != -1) {
					p = memory[p].nextPage;
					System.out.print(" -> " + p);
				}
			}
		}
	}

	public static void main(String[] args) {

		MemoryManager mem = new MemoryManager(30);
		mem.showFreeMemory();
		Process p1 = new Process();
		Process p2 = new Process();
		Process p3 = new Process();

		for (int i = 0; i < 20; i++) {
			double operation = Math.random();
			double proc = 1 + (int) (3 * Math.random());

			if (proc == 1) {
				if (operation < 0.7) {
					int memAllocated = mem.allocate(p1.computeMemoryRequred());
					if (memAllocated != -1)
						p1.getMemory(memAllocated);
				} else {
					if (p1.size() > 0) {
						mem.free(p1.returnMemory());
					}

				}
			} else if (proc == 2) {
				if (operation < 0.8) {
					int memAllocated = mem.allocate(p2.computeMemoryRequred());
					if (memAllocated != -1)
						p2.getMemory(memAllocated);
				} else {
					if (p2.size() > 0) {
						mem.free(p2.returnMemory());
					}
				}
			} else {
				if (operation < 0.8) {
					int memAllocated = mem.allocate(p3.computeMemoryRequred());
					if (memAllocated != -1)
						p3.getMemory(memAllocated);
				} else {
					if (p3.size() > 0) {
						mem.free(p3.returnMemory());
					}
				}
			}
			mem.showFreeMemory();
			System.out.print("\n< Process 1 > ");
			p1.showProcessMemory();
			System.out.print("\n< Process 2 > ");
			p2.showProcessMemory();
			System.out.print("\n< Process 3 > ");
			p3.showProcessMemory();
		}
	}
}
