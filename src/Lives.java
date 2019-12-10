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
	        g.fillOval(5, 3, 30, 30);
	        g.fillOval(40, 3, 30, 30);
	        g.fillOval(75, 3, 30, 30);
		} else if (lives == 2) {
			g.setColor(Color.RED);
			g.fillOval(5, 3, 30, 30);
	        g.fillOval(40, 3, 30, 30);
	        g.setColor(Color.DARK_GRAY);
	        g.fillOval(75, 3, 30, 30);
		} else if (lives == 1) {
			g.setColor(Color.RED);
			g.fillOval(5, 3, 30, 30);
			g.setColor(Color.DARK_GRAY);
	        g.fillOval(40, 3, 30, 30);
	        g.fillOval(75, 3, 30, 30);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillOval(5, 3, 30, 30);
	        g.fillOval(40, 3, 30, 30);
	        g.fillOval(75, 3, 30, 30);
		}
	}
}
