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
    private JTextField userInput;
    private JTextField currentScore;
    private JTextField levelDisplay;
    private JTextField highScore;

    // Game constants
    public static final int COURT_WIDTH = 1000;
    public static final int COURT_HEIGHT = 600;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 70;
    
    // Timer
    Timer timer;
    
    /*
     * CONSTRUCTOR
     * */

    public GameCourt(JTextField userInput, JTextField currentScore, JTextField highScore, 
    		JTextField levelDisplay) {
        
        // Creates border around the court area, JComponent method 
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	this.userInput = userInput;
    	this.currentScore = currentScore;
    	this.levelDisplay = levelDisplay;
    	this.highScore = highScore;

    	// Lets user press enter key to guess
    	userInput.setFocusable(true);
    	userInput.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String guess = userInput.getText().toLowerCase();
                	String answer = Asteroid.getWordFromDef(asteroid.getWord()).toLowerCase();
                	if (answer != null) {
                		if(guess.equals(answer)){
                        	userInput.setText("");
                        	score += scoreToAdd;
                        	currentScore.setText("Current Score: " + score);
                        	scoreToAdd = 100;
                        	changeAsteroid();
                        	asteroidCounter++;
                        } else {
                        	userInput.setText("");
                        	if (scoreToAdd >= 10) {
                        		scoreToAdd -= 10;
                        	}
                        }
                	}
                }
            }
    	});
    	
    	// Initializes timer
    	timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
    	
        timer.start();    
    }

    /**
     * Resets the game to initial state
     */
    public void reset() {
    	playing = true;
    	userInput.setEditable(true);
        lives = 3;
        level = 1;
        score = 0;
        asteroid = new GrayAsteroid(level, (int) (Math.random() * 500) + 100, 
        		COURT_WIDTH, COURT_HEIGHT);
        asteroidCounter = 0;
        userInput.setText("");
        timer.restart();
        highScore.setText("High Score: " + Game.getHighScorer() + ", " + Game.getHighScore());
    }

    /**
     * Called every time the timer triggers.
     */
    void tick() {
        if (playing && asteroidCounter <= 30) {
        	asteroid.move();
        	currentScore.setText("Current Score: " + score);
        	levelDisplay.setText("Level: " + level);
        	
        	// Changes level every 5 asteroids
        	if (asteroidCounter == 5) level = 2;
        	else if (asteroidCounter == 10) level = 3;
        	else if (asteroidCounter == 15) level = 4;
        	else if (asteroidCounter == 20) level = 5;
        	else if (asteroidCounter == 25) level = 6;

        	// Resets the asteroid when it drops completely out of bounds
        	if (asteroid.getPy() > COURT_HEIGHT + 100 || asteroid.getPx() > COURT_WIDTH + 100) {
        		lives--;
        		changeAsteroid();
        	} 
            
        	// Ends the game when the player wins or loses the game
            if (lives == 0 || asteroidCounter == 30) {
            	playing = false;
            }
            
            // updates the display
            repaint();
        } else {
        	// Doesn't let user adjust anything after the game is over
        	asteroid = null;
        	userInput.setEditable(false);
        	timer.stop();
        	
        	//Sends corresponding messages based on what happened
        	if (lives == 0) {
        		JOptionPane.showMessageDialog(null, "Game Over!");
        	} else if (asteroidCounter == 30) {
        		JOptionPane.showMessageDialog(null, "You won!");
        	}
        	
        	//Updates high score, if needed
        	Game.readHighScores();
        	if (score >= Game.getHighScore()) {
        		boolean goOn = false;
        		
        		while (!goOn) {
        			String name = JOptionPane.showInputDialog(null, 
        					"New High Score! Please enter your name.");
        			if (name == null) {
        				System.exit(0);
        			}
        			Game.setHighScore(name, score);
        			Game.readHighScores();
        			goOn = true;
        		}
        	}
        }
    }
    
    /*
     * Resets the asteroid.
     * There is a 1/100 chance of getting a red asteroid. (It most likely will make the player lose
     * 	the game.)
     * */
    void changeAsteroid() {
    	if ((int) (Math.random() * 100) + 1 == 5) {
    		asteroid = new RedAsteroid(level, COURT_WIDTH, COURT_HEIGHT);
    	} else {
    		asteroid = new GrayAsteroid(level, (int) (Math.random() * 500) + 100, 
    				COURT_WIDTH, COURT_HEIGHT);
    	}
		
    }
    
    /*
     * GETTERS
     * */
    
    public int getAsteroidCount() {
    	return asteroidCounter;
    }
    
    public int getLevel() {
    	return level;
    }
    
    public int getLives() {
    	return lives;
    }

    public int getScore() {
    	return score;
    }
    
    /*
     * JPANEL METHODS
     * */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File("files/galaxy.png"));
            }
        } catch (IOException e) {
            System.out.println("Can't find file path!");
        }
        g.drawImage(img, 0, 0, null);
        if (asteroid != null) asteroid.draw(g);
        new Lives(lives).draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

}