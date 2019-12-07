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
    
    private static String IMG_FILE = "files/red_asteroid-02.png";
    private static BufferedImage img;
    
	public RedAsteroid(int courtWidth, int courtHeight) {
		super(2, 9, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
		
		try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.BLACK);
        g.drawString(this.getWord(), this.getPx() + SIZE/3, this.getPy() + SIZE/2);
	}

}
