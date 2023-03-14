//Christopher Eslinger
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeGridPanel extends JPanel {
	private int rows;
	private int cols;
	private Cell[][] maze;

	// Extra credit
	public void generateMazeByDFS() {
		boolean[][] visited;
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	public void solveMaze() {
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows - 1][cols - 1];
		finish.setBackground(Color.RED);
		stack.push(start);
		// Implement your algorithm here
		while (!stack.isEmpty()) {
			Cell current = stack.pop();
			if (current == finish) {
				finish.setBackground(Color.red);
				return;
			}
			int row = current.row;
			int col = current.col;
			if (row > 0 && !visited(row - 1, col) && !maze[row][col].northWall) {
				Cell next = maze[row - 1][col];
				next.setBackground(Color.yellow);
				stack.push(next);
			}
			if (col > 0 && !visited(row, col - 1) && !maze[row][col].westWall) {
				Cell next = maze[row][col - 1];
				next.setBackground(Color.yellow);
				stack.push(next);
			}
			if (row < finish.row && !visited(row + 1, col) && !maze[row][col].southWall) {
				Cell next = maze[row + 1][col];
				next.setBackground(Color.yellow);
				stack.push(next);
			}
			if (col < finish.col && !visited(row, col + 1) && !maze[row][col].eastWall) {
				Cell next = maze[row][col + 1];
				next.setBackground(Color.yellow);
				stack.push(next);
			}
		}

	}

	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if (status.equals(Color.WHITE) || status.equals(Color.RED)) {
			return false;
		}

		return true;
	}

	public void generateMaze() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {

				if (row == 0 && col == 0) {
					continue;
				} else if (row == 0) {
					maze[row][col].westWall = false;
					maze[row][col - 1].eastWall = false;
				} else if (col == 0) {
					maze[row][col].northWall = false;
					maze[row - 1][col].southWall = false;
				} else {
					boolean north = Math.random() < 0.5;
					if (north) {
						maze[row][col].northWall = false;
						maze[row - 1][col].southWall = false;
					} else {
						maze[row][col].westWall = false;
						maze[row][col - 1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}

		this.repaint();
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800, 800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows, cols));
		this.maze = new Cell[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				maze[row][col] = new Cell(row, col);
				this.add(maze[row][col]);
			}
		}

		this.generateMaze();
		this.solveMaze();
	}
}
