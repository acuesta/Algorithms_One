package com.alcuvi.algorithms.week4;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

	private final int n;
	private final int[][] blocks;

	public Board(int[][] blocks) {
		this.n = blocks.length;
		this.blocks = blocks;
	}

	// board dimension n
	public int dimension() {
		return blocks.length;
	}

	// number of blocks out of place
	public int hamming() {
		int itemsOutOfPos = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int valueExpectedOnField = ((i * n) + (j + 1));
				if (this.blocks[i][j] != valueExpectedOnField && (this.blocks[i][j] != 0)) {
					itemsOutOfPos++;
				}
			}
		}
		return itemsOutOfPos;
	}

	public int manhattan() {
		int manhattan = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				final int valueExpectedOnField = ((i * n) + (j + 1));
				if (this.blocks[i][j] != valueExpectedOnField && (this.blocks[i][j] != 0)) {
					final int expectedRow = (blocks[i][j] - 1) / n;
					final int expectedColumn = (blocks[i][j] - 1) % n;
					final int distance = Math.abs(expectedRow - i) + Math.abs(expectedColumn - j);
					manhattan += distance;
				}
			}
		}

		return manhattan;
	}

	public boolean isGoal() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				final int valueExpectedOnField = ((i * n) + (j + 1));
				if (this.blocks[i][j] != valueExpectedOnField && (this.blocks[i][j] != 0)) {
					return false;
				}
			}
		}
		return true;
	}

	public Board twin() {
		int element1X = StdRandom.uniform(n), element1Y = StdRandom.uniform(n);
		int element2X = StdRandom.uniform(n), element2Y = StdRandom.uniform(n);

		final Board twinBoard = copy();

		if (element1X != element2X && element1Y != element2Y && twinBoard.blocks[element1X][element1Y] != 0
				&& twinBoard.blocks[element2X][element2Y] != 0) {
			twinBoard.swichItems(element1X, element1Y, element2X, element2Y);

		} else {
			return twin();
		}
		return twinBoard;
	}

	private Board copy() {
		int[][] newBlocks = new int[this.n][this.n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newBlocks[i][j] = this.blocks[i][j];
			}
		}
		return new Board(newBlocks);
	}

	private void swichItems(int element1X, int element1Y, int element2X, int element2Y) {
		int auxItem = this.blocks[element1X][element1Y];
		this.blocks[element1X][element1Y] = this.blocks[element2X][element2Y];
		this.blocks[element2X][element2Y] = auxItem;
	}

	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other.getClass() != this.getClass()) {
			return false;
		}
		final Board that = (Board) other;

		if (this.n != that.n) {
			return false;
		}

		boolean equals = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] != that.blocks[i][j]) {
					equals = false;
				}
			}
		}
		return equals;
	}

	public Iterable<Board> neighbors() {
		final Queue<Board> neighborsQueue = new Queue<Board>();

		int x = 0, y = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] == 0) {
					x = i;
					y = j;
					break;
				}
			}
		}

		if (x != 0) {
			addBuildNeighboard(x, y, x - 1, y, neighborsQueue);
		}

		if (y != 0) {
			addBuildNeighboard(x, y, x, y - 1, neighborsQueue);
		}

		if (y != this.n - 1) {
			addBuildNeighboard(x, y, x, y + 1, neighborsQueue);
		}

		if (x != this.n - 1) {
			addBuildNeighboard(x, y, x + 1, y, neighborsQueue);
		}

		return neighborsQueue;
	}

	private void addBuildNeighboard(int x, int y, int x2, int y2, Queue<Board> neighborsQueue) {
		final Board newBoard = buildNeighboard(x, y, x2, y2);
		neighborsQueue.enqueue(newBoard);
	}

	private Board buildNeighboard(int x, int y, int toX, int toY) {
		final Board neighborBoard = copy();
		neighborBoard.swichItems(x, y, toX, toY);
		return neighborBoard;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
	}

}
