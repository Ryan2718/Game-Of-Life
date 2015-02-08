package gameOfLife;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/** One cell on the grid for Game Of Life. Can be alive or dead.
 * @author Ryan Forsyth (c) 2014
 */
public class GridCell extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int BORDER_THICKNESS;
	public GridCell(int borderThickness) {
		super();
		BORDER_THICKNESS = borderThickness;
		Border border = BorderFactory.createLineBorder(Color.BLACK, 
				BORDER_THICKNESS);
		setBorder(border);
		setBackground(Color.WHITE);
		addMouseListener(new CellListener());
	}
	public GridCell(GridCell original) {
		this(original.BORDER_THICKNESS);
		setBackground(original.getBackground());
	}
	private class CellListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (getBackground() == ClickableGrid.DEAD) {
				setBackground(ClickableGrid.ALIVE);
			} else {
				setBackground(ClickableGrid.DEAD);
			}
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
}
