import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Lives extends JComponent {
	
	private int lives;
	
	public Lives (int lives) {
		this.lives = lives;
	}
	
	public void draw(Graphics g) {
		if (lives == 3) {
			g.setColor(Color.RED);
	        g.fillOval(0, 0, 15, 15);
	        g.fillOval(15, 0, 15, 15);
	        g.fillOval(30, 0, 15, 15);
		} else if (lives == 2) {
			g.setColor(Color.RED);
	        g.fillOval(0, 0, 15, 15);
	        g.fillOval(15, 0, 15, 15);
	        g.setColor(Color.DARK_GRAY);
	        g.fillOval(30, 0, 15, 15);
		} else if (lives == 1) {
			g.setColor(Color.RED);
	        g.fillOval(0, 0, 15, 15);
	        g.setColor(Color.DARK_GRAY);
	        g.fillOval(15, 0, 15, 15);
	        g.fillOval(30, 0, 15, 15);
		} else {
			g.setColor(Color.DARK_GRAY);
	        g.fillOval(0, 0, 15, 15);
	        g.fillOval(15, 0, 15, 15);
	        g.fillOval(30, 0, 15, 15);
		}
	}
}
