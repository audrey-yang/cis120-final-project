import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RedAsteroid extends Asteroid {
	
	public static final int SIZE = 150;
	public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = -10;
    public static double accely = 1.5;
    
    private static String IMG_FILE = "files/red_asteroid-02.png";
    private static BufferedImage img;
    
	public RedAsteroid(int level, int courtWidth, int courtHeight) {
		super(2, level, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
		
		try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
        	System.out.println("Not a valid path!");
        }
	}
	
	/*
	 * Moves the asteroid with acceleration 1.5 (super fast)
	 * */
	@Override
	public void move() {
		this.setVy((int) (this.getVy() * accely));
		this.setPx(this.getPx() + this.getVx());
		this.setPy(this.getPy() + this.getVy());
    }
	
	/*
	 * Draws the asteroid with the gray asteroid image and writes text above
	 * */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.BLACK);
        g.drawString(this.getWord(), this.getPx() + SIZE/4, this.getPy() + SIZE/2);
	}

}
