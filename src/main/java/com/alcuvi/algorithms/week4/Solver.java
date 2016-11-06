package com.alcuvi.algorithms.week4;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private final SearchNode gameTree;
	private final MinPQ<SearchNode> minPriorityQueue;
	private final SearchNode searchNodeSolution;

	public Solver(final Board initial) {

		if (initial == null) {
			throw new NullPointerException();
		}

		this.gameTree = new SearchNode(initial, null, 0);
		this.minPriorityQueue = new MinPQ<SearchNode>();

		if (initial.isGoal()) {
			this.searchNodeSolution = this.gameTree;

		} else {
			this.minPriorityQueue.insert(this.gameTree);
			final SearchNode twinNode = new SearchNode(initial.twin(), null, 0);
			this.minPriorityQueue.insert(twinNode);
			this.searchNodeSolution = solveBoard();
		}
	}

	public boolean isSolvable() {
		return this.searchNodeSolution != null;
	}

	public int moves() {
		if (this.searchNodeSolution == null) {
			return -1;
		}

		return this.searchNodeSolution.movements + this.searchNodeSolution.board.manhattan();
	}

	public Iterable<Board> solution() {
		if (this.searchNodeSolution == null) {
			return null;
		}

		final Stack<Board> solutionPath = new Stack<Board>();
		SearchNode node = this.searchNodeSolution;

		while (node != null) {
			solutionPath.push(node.board);
			node = node.previousSearchNode;
		}
		return solutionPath;
	}

	private SearchNode solveBoard() {
		final SearchNode solved;
		SearchNode minSN = this.minPriorityQueue.delMin();

		while (!minSN.board.isGoal()) {
			insertNeighbors(minSN);
			minSN = this.minPriorityQueue.delMin();
		}

		if (isSolvable(minSN)) {
			solved = minSN;
		} else {
			solved = null;
		}

		return solved;
	}

	private boolean isSolvable(final SearchNode node) {
		SearchNode currentNode = node;
		while (currentNode.previousSearchNode != null) {
			currentNode = currentNode.previousSearchNode;
		}
		return currentNode.board == this.gameTree.board;
	}

	private void insertNeighbors(final SearchNode searchNode) {
		for (final Board board : searchNode.board.neighbors()) {
			if (!isRepeated(searchNode, board)) {
				this.minPriorityQueue.insert(new SearchNode(board, searchNode, searchNode.movements + 1));
			}
		}
	}

	private boolean isRepeated(final SearchNode searchNode, final Board board) {
		if (searchNode.previousSearchNode == null) {
			return false;
		}
		return searchNode.previousSearchNode.board.equals(board);
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();

		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");

		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

	private class SearchNode implements Comparable<SearchNode> {
		private final Board board;
		private final SearchNode previousSearchNode;
		private final int movements;

		public SearchNode(final Board board, final SearchNode previousSarchNode, final int movements) {
			this.board = board;
			this.previousSearchNode = previousSarchNode;
			this.movements = movements;
		}

		public int compareTo(final SearchNode that) {
			if (this.board.manhattan() + this.movements < that.board.manhattan() + that.movements) {
				return -1;
			}

			if (this.board.manhattan() + this.movements > that.board.manhattan() + that.movements) {
				return 1;
			}

			if (this.board.hamming() + this.movements < that.board.hamming() + that.movements) {
				return -1;
			}

			if (this.board.hamming() + this.movements > that.board.hamming() + that.movements) {
				return 1;
			}

			return 0;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append(" movements:  ").append(this.movements).append("\n");
			s.append(" manhattan:  ").append(this.board.manhattan()).append("\n");
			s.append(board).append("\n");
			return s.toString();
		}

	}

}