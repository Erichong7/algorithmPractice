package midTerm;

public class MazeWall {

	int[][] maze;
	int[][] cost;
	int callCount;
	String ret;

	public MazeWall(int[][] input) {
		maze = input;
		int n = maze.length;
		cost = new int[n][n];
	}

	public void resetCount() {
		callCount = 0;
	}

	public int getCount() {
		return callCount;
	}

	public int minCost(int i, int j) {
		callCount++;

		if (i == 0 && j == 0) {
			return maze[0][0];
		} else if (i == 0) {
			return maze[i][j] + minCost(i, j - 1);
		} else if (j == 0) {
			return maze[i][j] + minCost(i - 1, j);
		} else if (maze[i - 1][j] == 999) {
			return maze[i][j] + minCost(i, j - 1);
		} else if (maze[i][j - 1] == 999) {
			return maze[i][j] + minCost(i - 1, j);
		} else {
			return maze[i][j] + Math.min(minCost(i - 1, j), minCost(i, j - 1));
		}
	}

	public int minCostAll(int i, int j) {
		if (i == 0 && j == 0) {
			return cost[0][0] = maze[i][j];
		} else if (j == 0) {
			cost[i][j] = maze[i][j];
			return minCostAll(i - 1, 6);
		} else {
			cost[i][j] = maze[i][j];
			return minCostAll(i, j - 1);
		}
	}

	public void showCost() {
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				System.out.printf("%5d", cost[i][j]);
			}
			System.out.println();
		}
	}

	public String pathToString(int i, int j) {
		ret = "(" + i + "," + j + ") ";
		if (i == 0 && j == 0) {
			return ret;
		}
		if (i == 0) {
			ret += pathToString(i, j - 1);
			return ret;
		}
		if (j == 0) {
			ret += pathToString(i - 1, j);
			return ret;
		}

		int min = Math.min(minCost(i - 1, j), minCost(i, j - 1));

		if (min == minCost(i - 1, j)) {
			ret += pathToString(i - 1, j);
		} else if (min == minCost(i, j - 1)) {
			ret += pathToString(i, j - 1);
		}
		return ret;
	}

	public static void main(String[] args) {
		int[][] input = { { 3, 2, 5, 1, 4, 3, 6 }, { 2, 999, 999, 5, 999, 999, 1 }, { 5, 999, 9, 6, 3, 5, 4 },
				{ 1, 999, 3, 8, 6, 1, 7 }, { 7, 999, 8, 999, 9, 2, 2 }, { 8, 6, 2, 999, 6, 9, 5 },
				{ 2, 1, 7, 2, 4, 3, 1 }, };

		MazeWall pf = new MazeWall(input);

		pf.resetCount();
		System.out.println("2 - 1) MinimumCost = " + pf.minCost(input.length - 1, input.length - 1));
		System.out.println("Recursive Call Count = " + pf.getCount());

		pf.resetCount();
		System.out.println("2 - 2) Cost Matrix");
		pf.minCostAll(input.length - 1, input.length - 1);
		pf.showCost();
		System.out.println("Recursive Call Count with Cost Matrix = " + pf.getCount());

		System.out.println("2 - 3) Minimum Cost Path");
		System.out.println(pf.pathToString(input.length - 1, input.length - 1));

	}

}
