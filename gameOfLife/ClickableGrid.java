package gameOfLife;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/** The grid that the Game Of Life is played on.
 * @author Ryan Forsyth (c) 2014
 */
public class ClickableGrid extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int PANEL_HEIGHT = 450;
	public static final int PANEL_WIDTH = 900;
	public final int NUM_ROWS;
	public final int NUM_COLS;
	private final int DIMENSION;
	private int delay;
	public final static Color ALIVE = Color.BLACK;
	public final static Color DEAD = Color.WHITE;
	private GridCell[][] cell;
	private Timer timer;
	public ClickableGrid(int numRows, int numCols, int dimension) {
		super(new GridLayout(numRows, numCols));
		NUM_ROWS = numRows;
		NUM_COLS = numCols;
		DIMENSION = dimension;
		cell = new GridCell[numRows][numCols];
		delay = 1000;
		timer = new Timer(delay, new NextGenerationListener());
		for (int row = 0; row < NUM_ROWS; row ++) {
			for (int col = 0; col < NUM_COLS; col++) {
				cell[row][col] = new GridCell(DIMENSION);
				add(cell[row][col]);
			}
		}
		setBackground(TotalPanel.FOREST_GREEN);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	public ClickableGrid(ClickableGrid original) {
		/* Instantiate a new empty ClikcableGrid. */
		this(original.NUM_ROWS, original.NUM_COLS, original.DIMENSION);
		/* Make a copy of each GridCell. to get each cell to be the same 
		 * color as the one in the original.
		 */
		for (int row = 0; row < this.cell.length; row++) {
			for (int col = 0; col < this.cell[row].length; col++) {
				this.cell[row][col] = new GridCell(original.cell[row][col]);
			}
		}
	}
	public void updateDelay(int delay) {
		timer.setDelay(delay);
	}
	public GridCell[][] getCell() {
		return cell;
	}
	public void playGameOfLife() {
		timer.start();
	}
	public void stopGame() {
		if (gameInProcess()) {
			timer.stop();
		}
	}
	public boolean gameInProcess() {
		return timer.isRunning();
	}
	public void clearGrid() {
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				cell[row][col].setBackground(DEAD);
				cell[row][col].repaint();
			}
		}
		repaint();
	}
	
	public void determineSurvival() {
		boolean[][] survivalArray = survives();
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				boolean survives = survivalArray[row][col];
				if (survives) {
					this.cell[row][col].setBackground(ALIVE);
				} else {
					this.cell[row][col].setBackground(DEAD);
				}
			}
		}	
	}
	
	public boolean[][] survives() {
		boolean[][] survivors = new boolean[NUM_ROWS][NUM_COLS];
		int[][] livingNeighboursArray = livingNeighbours();
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				GridCell currentCell = cell[row][col];
				int livingNeighbours = livingNeighboursArray[row][col];
				survivors[row][col] = survives(currentCell, livingNeighbours);
			}
		}
		return survivors;
	}
	
	public boolean survives(GridCell currentCell, int livingNeighbours) {
		boolean survives;
		if (currentCell.getBackground() == ALIVE) {
			boolean underPopulation = livingNeighbours < 2;
			boolean overPopulation = livingNeighbours > 3;
			if (underPopulation || overPopulation) {
				survives = false;
			} else {
				survives = true;
			}
		} else {
			boolean reproduction = livingNeighbours == 3;
			if (reproduction) {
				survives = true;
			} else {
				survives = false;
			}
		}
		return survives;
	}
	
	public int[][] livingNeighbours() {
		int[][] livingNeighbours = new int[NUM_ROWS][NUM_COLS];
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				livingNeighbours[row][col] = countLivingNeighbours(row, col);
			}
		}
		return livingNeighbours;
	}
	
	public int countLivingNeighbours(int cellRow, int cellCol) {
		
		boolean upperLeftAlive;
		if (cellRow !=0 && cellCol != 0) {
			upperLeftAlive = 
				(cell[cellRow - 1][cellCol - 1].getBackground() == ALIVE);
		} else {
			upperLeftAlive = false;
		}
		
		boolean upperAlive;
		if (cellRow !=0) {
			upperAlive =
				(cell[cellRow - 1][cellCol].getBackground() == ALIVE);
		} else {
			upperAlive = false;
		} 
		
		boolean upperRightAlive;
		if (cellRow !=0 && cellCol != NUM_COLS - 1) {
			upperRightAlive = 
				(cell[cellRow - 1][cellCol + 1].getBackground() == ALIVE);
		} else {
			upperRightAlive = false;
		}
		
		boolean leftAlive;
		if (cellCol != 0) {
			leftAlive =
				(cell[cellRow][cellCol - 1].getBackground() == ALIVE);
		} else {
			leftAlive = false;
		}
				
		boolean rightAlive;
		if (cellCol != NUM_COLS - 1) {
			rightAlive =
				(cell[cellRow][cellCol + 1].getBackground() == ALIVE);
		} else {
			rightAlive = false;
		}
		
		boolean lowerLeftAlive;
		if (cellRow != NUM_ROWS - 1 && cellCol != 0) {
			lowerLeftAlive =
				(cell[cellRow + 1][cellCol - 1].getBackground() == ALIVE);
		} else {
			lowerLeftAlive = false;
		}
		
		boolean lowerAlive;
		if (cellRow != NUM_ROWS - 1) {
			lowerAlive = 
				(cell[cellRow + 1][cellCol].getBackground() == ALIVE);
		} else {
			lowerAlive = false;
		}
		
		boolean lowerRightAlive;
		if (cellRow != NUM_ROWS - 1 && cellCol != NUM_COLS - 1) {
			lowerRightAlive =
				(cell[cellRow + 1][cellCol + 1].getBackground() == ALIVE);
		} else {
			lowerRightAlive = false;
		}
		
		int livingNeighbourCounter = 0;
		if (upperLeftAlive) {
			livingNeighbourCounter++;
		}
		if (upperAlive) {
			livingNeighbourCounter++;
		}
		if (upperRightAlive) {
			livingNeighbourCounter++;
		}
		if (leftAlive) {
			livingNeighbourCounter++;
		}
		if (rightAlive) {
			livingNeighbourCounter++;
		}
		if (lowerLeftAlive) {
			livingNeighbourCounter++;
		}
		if (lowerAlive) {
			livingNeighbourCounter++;
		}
		if (lowerRightAlive) {
			livingNeighbourCounter++;
		}
		return livingNeighbourCounter;
	}
	private class NextGenerationListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			determineSurvival();
			for (int row = 0; row < NUM_ROWS; row++) {
				for (int col = 0; col < NUM_COLS; col++) {
					cell[row][col].repaint();
				}
			}
			revalidate();
			repaint();
		}
	}
}
