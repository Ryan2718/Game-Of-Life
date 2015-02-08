package gameOfLife;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/** The panel that holds all the buttons and the grid for the Game Of Life.
 * @author Ryan Forsyth (c) 2014
 */
public class TotalPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Color FOREST_GREEN = new Color(34, 139, 34);
	public TotalPanel() {
		setLayout(new BorderLayout(10, 10));
		setBackground(FOREST_GREEN);
		ButtonPanel topPanel = new ButtonPanel();
		add(topPanel, BorderLayout.NORTH);
		add(topPanel.getGrid(), BorderLayout.CENTER);
		PatternPanel patternPanel = new PatternPanel(topPanel);
		add(patternPanel, BorderLayout.EAST);
	}
}
