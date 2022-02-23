package challenge;

import java.util.Random;

public class Board {

	private int[][] board;
	private int rows;
	private int columns;
	private static final String pipe = "|";

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.board = new int[this.rows][this.columns];
	}

	public void intializeBoard() {
		Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = random.nextInt(2);
			}

		}
	}

	public void set(int row, int column, int value) {
		board[row][column] = value;
	}
	
	public int[][] getBoard(){
		return this.board;
	}
	
	public void copyBoard(int[][] board){
		this.board = board;
	}

	

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("---------------------------------------\n");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				stringBuilder.append(pipe);
				if (board[i][j] == 0) {
					stringBuilder.append(".");
				} else {
					stringBuilder.append("+");
				}
				if (j == columns - 1) {
					stringBuilder.append(pipe);
				}

			}

			stringBuilder.append("\n");

		}
		stringBuilder.append("---------------------------------------\n");
		return stringBuilder.toString();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
}
