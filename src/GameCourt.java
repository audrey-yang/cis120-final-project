import java.awt.*;
import java.awt.event.*;
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
    private int level = 0;
    private int lives = 3;

    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    private JTextField textBox;
    private JButton enter;

    // Game constants
    public static final int COURT_WIDTH = 1000;
    public static final int COURT_HEIGHT = 600;
    public static final int SQUARE_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status/*, JTextField textBox, JButton enter*/) {
        /* 
         * creates border around the court area, JComponent method 
         * */
    	this.status = status;
    	/*this.textBox = textBox;
    	this.enter = enter;*/
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        /*enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                if(textBox.getText().equals(asteroid.getWord())){
                	//asteroid.vaporize();
                } else {
                	textBox.setText("");
                }
            }
        }); */

   

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
        
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	playing = true;
        status.setText("Running...");
        lives = 3;
        level = 0;
        asteroid = new GrayAsteroid(level, COURT_WIDTH, COURT_HEIGHT);
        
        //textBox.setText("");
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	asteroid.move();
            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        asteroid.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

}