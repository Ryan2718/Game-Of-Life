package gameOfLife;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** The top panel with the start button, stop button, and speed slider.
 * @author Ryan Forsyth (c) 2014
 */
public class ButtonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton startButton, stopButton;
	private JLabel sliderLabel;
	private JSlider delaySlider;
	private ClickableGrid grid;
	public ButtonPanel() {
		ButtonListener listener = new ButtonListener();
		startButton = new JButton ("Start");
		startButton.addActionListener(listener);
		this.grid = new ClickableGrid(25, 50, 1);
		add(startButton);
		stopButton = new JButton ("Stop");
		stopButton.addActionListener(listener);
		add(stopButton);
		delaySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
		delaySlider.addChangeListener(new SliderListener());
		sliderLabel = new JLabel("Speed: ");
		add(sliderLabel);
		add(delaySlider);
		setBackground(TotalPanel.FOREST_GREEN);
		setPreferredSize(new Dimension(100, 100));
	}
	public ClickableGrid getGrid() {
		return grid;
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == startButton) {
				grid.playGameOfLife();
			} else if (event.getSource() == stopButton) {
				grid.stopGame();
			}
		}
	}
	
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			int value = delaySlider.getValue();
			int delay = 110*(10 - value);
			grid.updateDelay(delay);
		}
	}
	
}
