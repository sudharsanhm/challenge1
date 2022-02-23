package challenge;

public class BoardGame {

	private static final int ROWS = 10;
	private static final int COLUMNS = 10;

	/**
	 * main method execution starts
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board(ROWS, COLUMNS);
		board.intializeBoard();
		System.out.println(board);
		nextGeneration(board);
		System.out.println(board);
	}

	/**
	 * It calcultes next gen value based on 4 conditions
	 * 
	 * @param board
	 */
	private static void nextGeneration(Board board) {
		int nextGen[][] = new int[ROWS][COLUMNS];
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				int aliveNeighboursCount = countAliveNeighbours(board, row, column);
				step(board, nextGen, row, column, aliveNeighboursCount);
			}
		}
		board.copyBoard(nextGen);
	}

	/**
	 * 1. Any live cell with fewer than two live neighbors dies as if caused by
	 * underpopulation. 2. Any live cell with two or three live neighbors lives on
	 * to the next generation. 3. Any live cell with more than three live neighbors
	 * dies, as if by overcrowding. 4. Any dead cell with exactly three live
	 * neighbors becomes a live cell, as if by reproduction.
	 * 
	 * @param board
	 * @param nextGen
	 * @param row
	 * @param column
	 * @param aliveNeighboursCount
	 */
	private static void step(Board board, int nextGen[][], int row, int column, int aliveNeighboursCount) {
		int b[][] = board.getBoard();
		int val = b[row][column];
		if (val == 1) {
			if (aliveNeighboursCount < 2) {
				nextGen[row][column] = 0;
			} else if (aliveNeighboursCount == 2 || aliveNeighboursCount == 3) {
				nextGen[row][column] = 1;
			} else if (aliveNeighboursCount > 3) {
				nextGen[row][column] = 0;
			}
		} else {
			if (aliveNeighboursCount == 3) {
				nextGen[row][column] = 1;
			}
		}

	}

	/**
	 * count the left,right and diagonal neighbours
	 * 
	 * @param board
	 * @param row
	 * @param column
	 * @return
	 */
	private static int countAliveNeighbours(Board board, int row, int column) {
		int sum = 0;
		int[][] b = board.getBoard();

		sum += getVal(b, row - 1, column - 1);
		sum += getVal(b, row - 1, column);
		sum += getVal(b, row - 1, column + 1);

		sum += getVal(b, row, column - 1);
		sum += getVal(b, row + 1, column + 1);

		sum += getVal(b, row + 1, column - 1);
		sum += getVal(b, row + 1, column);
		sum += getVal(b, row + 1, column + 1);

		return sum;

	}

	/**
	 * Get vlaue from cell
	 * 
	 * @param b
	 * @param row
	 * @param column
	 * @return
	 */
	private static int getVal(int[][] b, int row, int column) {
		if (row < 0 || row >= ROWS) {
			return 0;
		} else if (column < 0 || column >= COLUMNS) {
			return 0;
		}
		return b[row][column];
	}
}
