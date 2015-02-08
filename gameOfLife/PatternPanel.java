package gameOfLife;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/** The panel for the different patterns to be selected in 
 * Conway's Game of Life.
 * @author Ryan Forsyth (c) 2014
 */
public class PatternPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ClickableGrid grid;
	private GridCell[][] cell;
	private JLabel stillLife, oscillators, spaceships, other;
	private JRadioButton freeDraw;
	private JRadioButton block, beehive, loaf, boat;
	private JRadioButton blinker, toad, beacon, pulsar;
	private JRadioButton glider, lightweightSpaceship;
	private JRadioButton rPentomino, diehard, acorn;
	private JRadioButton gliderGun, random;
	private ButtonGroup group;
	public PatternPanel(ButtonPanel buttonPanel) {
		grid = buttonPanel.getGrid();
		cell = grid.getCell();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(TotalPanel.FOREST_GREEN);
		setPreferredSize(new Dimension (400, ClickableGrid.PANEL_HEIGHT));
		stillLife = new JLabel("Still lifes: ");
		oscillators = new JLabel("Oscillators: ");
		spaceships = new JLabel("Spaceships: ");
		other = new JLabel("Other: ");
		freeDraw = new JRadioButton("Free Draw", true);
		block = new JRadioButton("Block");
		beehive = new JRadioButton("Beehive");
		loaf = new JRadioButton("Loaf");
		boat = new JRadioButton("Boat");
		blinker = new JRadioButton("Blinker");
		toad = new JRadioButton("Toad");
		beacon = new JRadioButton("Beacon");
		pulsar = new JRadioButton("Pulsar");
		glider = new JRadioButton("Glider");
		lightweightSpaceship = new JRadioButton("Lightweight Spaceship (LWSS)");
		rPentomino = new JRadioButton("R-pentomino");
		diehard = new JRadioButton("Diehard");
		acorn = new JRadioButton("Acorn");
		gliderGun = new JRadioButton("Gosper glider gun");
		random = new JRadioButton("Random");
		
		group = new ButtonGroup();
		group.add(freeDraw);
		group.add(block);
		group.add(beehive);
		group.add(loaf);
		group.add(boat);
		group.add(blinker);
		group.add(toad);
		group.add(beacon);
		group.add(pulsar);
		group.add(glider);
		group.add(lightweightSpaceship);
		group.add(rPentomino);
		group.add(diehard);
		group.add(acorn);
		group.add(gliderGun);
		group.add(random);
		
		PatternListener listener = new PatternListener();
		freeDraw.addActionListener(listener);
		block.addActionListener(listener);
		beehive.addActionListener(listener);
		loaf.addActionListener(listener);
		boat.addActionListener(listener);
		blinker.addActionListener(listener);
		toad.addActionListener(listener);
		beacon.addActionListener(listener);
		pulsar.addActionListener(listener);
		glider.addActionListener(listener);
		lightweightSpaceship.addActionListener(listener);
		rPentomino.addActionListener(listener);
		diehard.addActionListener(listener);
		acorn.addActionListener(listener);
		gliderGun.addActionListener(listener);
		random.addActionListener(listener);
		
		add(freeDraw);
		
		add(stillLife);
		add(block);
		add(beehive);
		add(loaf);
		add(boat);
		
		add(oscillators);
		add(blinker);
		add(toad);
		add(beacon);
		add(pulsar);
		
		add(spaceships);
		add(glider);
		add(lightweightSpaceship);
		
		add(other);
		add(rPentomino);
		add(diehard);
		add(acorn);
		add(gliderGun);
		add(random);
	}
	private class PatternListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == freeDraw) {
				grid.stopGame();
				grid.clearGrid();
			} else if (source == block) {
				drawBlock();
			} else if (source == beehive) {
				drawBeehive();
			} else if (source == loaf) {
				drawLoaf();
			} else if (source == boat) {
				drawBoat();
			} else if (source == blinker) {
				drawBlinker();
			} else if (source == toad) {
				drawToad();
			} else if (source == beacon) {
				drawBeacon();
			} else if (source == pulsar) {
				drawPulsar();
			} else if (source == glider) {
				drawGlider();
			} else if (source == lightweightSpaceship) {
				drawLWSS();
			} else if (source == rPentomino) {
				drawRPentomino();
			} else if (source == diehard) {
				drawDiehard();
			} else if (source == acorn) {
				drawAcorn();
			} else if (source == gliderGun) {
				drawGliderGun();
			} else if (source == random) {
				drawRandom();
			}
		}
		private void drawBlock() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(12, 26);
			makeAlive(13, 25);
			makeAlive(13, 26);
			grid.playGameOfLife();
		}
		private void drawBeehive() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(11, 26);
			makeAlive(11, 27);
			makeAlive(12, 28);
			makeAlive(13, 27);
			makeAlive(13, 26);
			grid.playGameOfLife();
		}
		private void drawLoaf() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(11, 26);
			makeAlive(11, 27);
			makeAlive(12, 28);
			makeAlive(13, 28);
			makeAlive(14, 27);
			makeAlive(13, 26);
			grid.playGameOfLife();
		}
		private void drawBoat() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(12, 26);
			makeAlive(13, 27);
			makeAlive(14, 26);
			makeAlive(13, 25);
			grid.playGameOfLife();
		}
		private void drawBlinker() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(12, 26);
			makeAlive(12, 27);
			grid.playGameOfLife();
		}
		
		private void drawToad() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(12, 26);
			makeAlive(12, 27);
			makeAlive(13, 24);
			makeAlive(13, 25);
			makeAlive(13, 26);
			grid.playGameOfLife();
		}
		private void drawBeacon() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(12, 25);
			makeAlive(12, 26);
			makeAlive(13, 25);
			makeAlive(14, 28);
			makeAlive(15, 28);
			makeAlive(15, 27);
			grid.playGameOfLife();
		}
		private void drawPulsar() {
			grid.stopGame();
			grid.clearGrid();
			
			makeAlive(10, 12);
			makeAlive(10, 13);
			makeAlive(10, 14);
			makeAlive(10, 18);
			makeAlive(10, 19);
			makeAlive(10, 20);
			
			makeAlive(12, 10);
			makeAlive(12, 15);
			makeAlive(12, 17);
			makeAlive(12, 22);
			
			makeAlive(13, 10);
			makeAlive(13, 15);
			makeAlive(13, 17);
			makeAlive(13, 22);
			
			makeAlive(14, 10);
			makeAlive(14, 15);
			makeAlive(14, 17);
			makeAlive(14, 22);
			
			makeAlive(15, 12);
			makeAlive(15, 13);
			makeAlive(15, 14);
			makeAlive(15, 18);
			makeAlive(15, 19);
			makeAlive(15, 20);
			
			makeAlive(17, 12);
			makeAlive(17, 13);
			makeAlive(17, 14);
			makeAlive(17, 18);
			makeAlive(17, 19);
			makeAlive(17, 20);
			
			makeAlive(18, 10);
			makeAlive(18, 15);
			makeAlive(18, 17);
			makeAlive(18, 22);
			
			makeAlive(19, 10);
			makeAlive(19, 15);
			makeAlive(19, 17);
			makeAlive(19, 22);
			
			makeAlive(20, 10);
			makeAlive(20, 15);
			makeAlive(20, 17);
			makeAlive(20, 22);
			
			makeAlive(22, 12);
			makeAlive(22, 13);
			makeAlive(22, 14);
			makeAlive(22, 18);
			makeAlive(22, 19);
			makeAlive(22, 20);
			
			grid.playGameOfLife();
		}
		private void drawGlider() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(5, 5);
			makeAlive(6, 6);
			makeAlive(7, 6);
			makeAlive(7, 5);
			makeAlive(7, 4);
			grid.playGameOfLife();
		}
		private void drawLWSS() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(13, 15);
			makeAlive(14, 15);
			makeAlive(15, 15);
			makeAlive(15, 16);
			makeAlive(15, 17);
			makeAlive(15, 18);
			makeAlive(14, 19);
			makeAlive(12, 19);
			makeAlive(12, 16);
			grid.playGameOfLife();
		}
		private void drawRPentomino() {
			grid.stopGame();
			grid.clearGrid();
			makeAlive(10, 25);
			makeAlive(9, 26);
			makeAlive(10, 26);
			makeAlive(11, 26);
			makeAlive(9, 27);
			grid.playGameOfLife();
		}
		private void drawDiehard() {
			grid.stopGame();
			grid.clearGrid();
			
			makeAlive(10, 25);
			makeAlive(10, 26);
			makeAlive(11, 26);
			
			makeAlive(9, 31);
			
			makeAlive(11, 30);
			makeAlive(11, 31);
			makeAlive(11, 32);
			
			grid.playGameOfLife();
		}
		private void drawAcorn() {
			grid.stopGame();
			grid.clearGrid();
			
			makeAlive(10, 25);
			makeAlive(10, 26);
			makeAlive(8, 26);
			
			makeAlive(9, 28);
			makeAlive(10, 29);
			makeAlive(10, 30);
			makeAlive(10, 31);
			
			grid.playGameOfLife();
		}
		private void drawGliderGun() {
			grid.stopGame();
			grid.clearGrid();
			
			makeAlive(10, 6);
			makeAlive(10, 7);
			makeAlive(11, 6);
			makeAlive(11, 7);
			
			makeAlive(10, 16);
			makeAlive(11, 16);
			makeAlive(12, 16);
			makeAlive(9, 17);
			makeAlive(13, 17);
			makeAlive(8, 18);
			makeAlive(14, 18);
			makeAlive(8, 19);
			makeAlive(14, 19);
			makeAlive(11, 20);
			makeAlive(9, 21);
			makeAlive(13, 21);
			makeAlive(10, 22);
			makeAlive(11, 22);
			makeAlive(12, 22);
			makeAlive(11, 23);
			
			makeAlive(8, 26);
			makeAlive(9, 26);
			makeAlive(10, 26);
			makeAlive(8, 27);
			makeAlive(9, 27);
			makeAlive(10, 27);
			makeAlive(7, 28);
			makeAlive(11, 28);
			makeAlive(6, 30);
			makeAlive(7, 30);
			makeAlive(11, 30);
			makeAlive(12, 30);
			
			makeAlive(8, 40);
			makeAlive(8, 41);
			makeAlive(9, 40);
			makeAlive(9, 41);
			grid.playGameOfLife();
		}
		private void drawRandom() {
			grid.stopGame();
			grid.clearGrid();
			int area = grid.NUM_ROWS*grid.NUM_COLS;
			int numberOfLivingCells = (int) (Math.random()*area/4);
			int randomRow, randomCol;
			for (int index = 0; index < numberOfLivingCells; index++) {
				randomRow = (int) (Math.random()*grid.NUM_ROWS);
				randomCol = (int) (Math.random()*grid.NUM_COLS);
				makeAlive(randomRow, randomCol);
			}
			grid.playGameOfLife();
		}
		private void makeAlive(int row, int col) {
			cell[row][col].setBackground(ClickableGrid.ALIVE);
		}
	}
}
