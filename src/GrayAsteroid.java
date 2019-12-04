import java.awt.Color;
import java.awt.Graphics;

public class GrayAsteroid extends Asteroid {

	public GrayAsteroid(int level, int courtWidth, int courtHeight) {
		super(level + 1, 0, 0, 0, 100, 100, courtWidth, courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GRAY);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawString(this.getWord(), this.getPx(), this.getPy());
	}

}
