import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GrayAsteroid extends Asteroid {
	
	public static final int SIZE = 200;
    public static final int INIT_POS_Y = -SIZE / 2;
	
    
    private static String IMG_FILE = "files/gray_asteroid-01.png";
    private static BufferedImage img;
    
	public GrayAsteroid(int level, int initPosX, int courtWidth, int courtHeight) {
		super(level, 0, initPosX, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
		
		try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
        	System.out.println("Not a valid path!");
        }
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.BLACK);
        g.drawString(this.getWord(), this.getPx() + SIZE/3, this.getPy() + SIZE/2); 
	}

}
