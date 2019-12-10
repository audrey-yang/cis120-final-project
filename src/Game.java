import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Game implements Runnable {
	
	private static final String HIGH_SCORES_FILE = "files/high_scores.txt";
	private static int highScore; 
	private static String highScorer;
	
	/*
	 * SET HIGH SCORE METHODS
	 * */
	public static int getHighScore() {
		return highScore;
	}
	
	public static String getHighScorer() {
		return highScorer;
	}
	
	/*
	 * Writes high score and high scorer to the file
	 * */
	public static  void setHighScore(String name, int score) {
		try {
            FileWriter fw = new FileWriter(HIGH_SCORES_FILE);
            BufferedWriter bf = new BufferedWriter(fw);
            bf.write(name + "\t" + score);
            bf.close();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
	}
	
	/*
	 * Reads high score and high scorer from the file
	 * */
	public static void readHighScores() {
		FileLineIterator fl = new FileLineIterator(HIGH_SCORES_FILE);
    	if (!fl.hasNext()) {
    		highScorer = "No one yet!";
    		highScore = 0;
    	}
    	if (fl.hasNext()) {
    		String[] words = fl.next().split("\t");
    		highScorer = words[0];
    		highScore = Integer.parseInt(words[1]);
    	}
	}
	
	/*
	 * Wrapper around the Asteroid method to get user input and filter out bad files
	 * */
	public void importFile() {
		boolean okay = false;
		
		while (!okay) { 
			String filePath = JOptionPane.showInputDialog(null, 
					"Enter the file path (must be a .txt file)");
			if (filePath == null) {
				System.exit(0);
			}
			
			if (filePath.contains(".txt")) {
				try {
					Asteroid.importVocab(filePath);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Invalid file path!");
					continue;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Must be a .txt file!");
				continue;
			}
			
			okay = true;
		}
	}
	
	/*
	 * RUN FUNCTION - Lays out the game components, starts the game
	 * */
	public void run() {
		importFile();
		
		// Top-level frame in which game components live
        final JFrame frame = new JFrame("Gravity");
        frame.setBackground(Color.BLACK);
        frame.setLocation(100, 100);
        frame.setResizable(false);
        
        //user input stuffs
        final JPanel textEnter = new JPanel();
        textEnter.setBackground(Color.BLACK);
        frame.add(textEnter, BorderLayout.SOUTH);
        final JTextField userInput = new JTextField("");
        userInput.setPreferredSize(new Dimension(200, 20));
        userInput.setEditable(true);
        textEnter.add(userInput);
       
        //top panel
        final JPanel north = new JPanel();
        north.setLayout(new GridBagLayout());
        north.setBackground(Color.BLACK);
        frame.add(north, BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();
        
        // score display
        final JPanel scores = new JPanel();
        scores.setBackground(Color.BLACK);
        scores.setLayout(new GridLayout());
        final JTextField currentScoreDisplay = new JTextField("Current Score: 0");
        currentScoreDisplay.setEditable(false);
        currentScoreDisplay.setPreferredSize(new Dimension(200, 20));
        readHighScores();
        currentScoreDisplay.setBackground(Color.BLACK);
        currentScoreDisplay.setForeground(Color.WHITE);
        final JTextField highScoreDisplay = 
        		new JTextField("High Score: " + getHighScorer() + ", " + getHighScore());
        
        highScoreDisplay.setEditable(false);
        highScoreDisplay.setPreferredSize(new Dimension(200, 20));
        highScoreDisplay.setForeground(Color.WHITE);
        highScoreDisplay.setBackground(Color.BLACK);
        
        scores.add(currentScoreDisplay);
        scores.add(highScoreDisplay);
        north.add(scores);
        scores.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Reset button
        final JPanel control_panel = new JPanel();
        control_panel.setBackground(Color.BLACK);
        north.add(control_panel);
        control_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // level display
        final JTextField level = new JTextField("Level: 1");
        level.setEditable(false);
        level.setBackground(Color.BLACK);
        level.setForeground(Color.WHITE);
        north.add(level);
        level.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        // Main playing area
        final GameCourt court = new GameCourt(userInput, currentScoreDisplay, 
        		highScoreDisplay, level);
        frame.add(court, BorderLayout.CENTER);
        
        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        

        // Start game
        court.reset();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
	
}
