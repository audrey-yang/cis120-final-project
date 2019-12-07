import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
    // the state of the game logic
    private Asteroid asteroid; 
    private int asteroidCounter = 0;
    private int level = 1;
    private int lives = 3;
    private int score = 0;
    private int scoreToAdd = 100;
   
    public boolean playing = false; // whether the game is running 
    //private JLabel status; // Current status text, i.e. "Running..."
    private JTextField userInput;
    private JButton enter;
    private JTextField currentScore;
    private JPanel lifeDisplay;

    // Game constants
    public static final int COURT_WIDTH = 1000;
    public static final int COURT_HEIGHT = 600;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 70;

    public GameCourt(JTextField userInput, JButton enter, JTextField currentScore, JPanel lifeDisplay) {
        /* 
         * creates border around the court area, JComponent method 
         * */
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	this.userInput = userInput;
    	this.enter = enter;
    	this.currentScore = currentScore;
    	this.lifeDisplay = lifeDisplay;
    	
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
            	String guess = userInput.getText().toLowerCase();
            	String answer = Asteroid.getWordFromDef(asteroid.getWord()).toLowerCase();
            	if(guess.equals(answer)){
                	userInput.setText("");
                	score += scoreToAdd;
                	currentScore.setText("Current Score: " + score);
                	scoreToAdd = 100;
                	changeAsteroid();
                	asteroidCounter++;
                	System.out.println(asteroidCounter + " " + level);
                } else {
                	userInput.setText("");
                	scoreToAdd -= 10;
                }
            }
        }); 
   

        /* 
         * The timer is an object which triggers an action periodically with the given INTERVAL. We
         * register an ActionListener with this timer, whose actionPerformed() method is called each
         * time the timer triggers. We define a helper method called tick() that actually does
         * everything that should be done in a single timestep. 
         * */
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        
        timer.start(); 
        if (lives == 0) {
        	timer.stop();
        }
        
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	playing = true;
        lives = 3;
        level = 1;
        score = 0;
        asteroid = new GrayAsteroid(level, (int) (Math.random() * 500) + 100, 
        		COURT_WIDTH, COURT_HEIGHT);
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	asteroid.move();
        	currentScore.setText("Current Score: " + score);
        	if (asteroidCounter == 5) level = 2;
        	else if (asteroidCounter == 10) level = 3;
        	else if (asteroidCounter == 15) level = 4;
        	else if (asteroidCounter == 20) level = 5;
        	else if (asteroidCounter == 25) level = 6;
        	
        	
        	
        	if (asteroid.getPy() > COURT_HEIGHT + 100 || asteroid.getPx() > COURT_WIDTH + 100) {
        		lives--;
        		changeAsteroid();
        	} 
 
            // update the display
            repaint();
        }
    }
    
    private void changeAsteroid() {
    	if (asteroidCounter % level == 1) {
    		asteroid = new RedAsteroid(COURT_WIDTH, COURT_HEIGHT);
    	} else {
    		asteroid = new GrayAsteroid(level, (int) Math.random() * 500 + 100, 
    				COURT_WIDTH, COURT_HEIGHT);
    	}
		
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File("files/galaxy.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        g.drawImage(img, 0, 0, null);
        asteroid.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

}