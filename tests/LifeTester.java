package tests;
import gameOfLife.ClickableGrid;
import gameOfLife.GridCell;
import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;

/** Tests of the Game of Life program.
 * @author Ryan Forsyth (c) 2014
 */
public class LifeTester {

	@Test
	public void testBlinker() {
		ClickableGrid grid = new ClickableGrid(25, 50, 1);
		GridCell top = grid.getCell()[10][9];
		GridCell middle = grid.getCell()[10][10];
		GridCell bottom = grid.getCell()[10][11];
		assertTrue(middle.getBackground() == Color.WHITE);
		top.setBackground(Color.BLACK);
		middle.setBackground(Color.BLACK);
		bottom.setBackground(Color.BLACK);
		assertTrue(middle.getBackground() == Color.BLACK);
		assertTrue(grid.countLivingNeighbours(10, 9) == 1);
		assertTrue(grid.countLivingNeighbours(10, 10) == 2);
		assertTrue(grid.countLivingNeighbours(10, 11) == 1);
		assertTrue(grid.countLivingNeighbours(10, 20) == 0);
		assertFalse(grid.survives(top, 1));
		assertTrue(grid.survives(middle, 2));
		assertFalse(grid.survives(bottom, 1));
		grid.determineSurvival();
		assertTrue(top.getBackground() == Color.WHITE);
		assertTrue(middle.getBackground() == Color.BLACK);
		assertTrue(bottom.getBackground() == Color.WHITE);
	}

}
