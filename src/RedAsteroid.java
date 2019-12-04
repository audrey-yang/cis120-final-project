import java.awt.Color;
import java.awt.Graphics;

public class RedAsteroid extends Asteroid {
	
	public static final int SIZE = 100;
    public static final int INIT_POS_X = 170;
    public static final int INIT_POS_Y = 170;
    public static final int INIT_VEL_X = 2;
    public static final int INIT_VEL_Y = 3;
    
	
	public RedAsteroid(int courtWidth, int courtHeight) {
		super(2, 8, 0, 0, SIZE, SIZE, courtWidth, courtHeight);
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawString(this.getWord(), this.getPx(), this.getPy());
	}

}
