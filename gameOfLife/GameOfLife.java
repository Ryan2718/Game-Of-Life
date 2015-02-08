package gameOfLife;
import javax.swing.JFrame;

/** Plays Conway's Game Of Life.
 * @author Ryan Forsyth (c) 2014
 */
public class GameOfLife {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Conway's Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TotalPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
