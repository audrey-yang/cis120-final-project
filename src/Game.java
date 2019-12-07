import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

public class Game implements Runnable {
	
	private static final String HIGH_SCORES_FILE = "files/high_scores.txt";
	private int highScore; 
	private String highScorer;
	
	public int getHighScore() {
		return highScore;
	}
	
	public String getHighScorer() {
		return highScorer;
	}
	
	public void setHighScore(String name, int score) {
		try {
            FileWriter fw = new FileWriter(HIGH_SCORES_FILE);
            BufferedWriter bf = new BufferedWriter(fw);
            bf.write(name + "\t" + score);
            bf.close();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
	}
	
	public void readHighScores() {
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
	
	public void run() {
		//get file path
		boolean okay = false;
		
		while (!okay) {
			String filePath = JOptionPane.showInputDialog(null, "Enter the file path");
			if (filePath == null) {
				System.exit(0);
			}
			
			try {
				Asteroid.importVocab(filePath);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Invalid file path!");
				continue;
			}
			okay = true;
		}
		
		// Top-level frame in which game components live
        final JFrame frame = new JFrame("Gravity");
        frame.setBackground(Color.CYAN);
        frame.setLocation(100, 100);
        frame.setResizable(false);

        //south jpanel so input and running don't overlap
        /*final JPanel south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.PAGE_AXIS));
        frame.add(south, BorderLayout.SOUTH);*/
        
        //user input stuffs
        final JPanel textEnter = new JPanel();
        frame.add(textEnter, BorderLayout.SOUTH);
        final JTextField userInput = new JTextField("");
        userInput.setPreferredSize(new Dimension(100, 20));
        userInput.setEditable(true);
        textEnter.add(userInput);
        final JButton enter = new JButton("Go!");
        textEnter.add(enter);
        
        // Status panel
        /*final JPanel status_panel = new JPanel();
        south.add(status_panel);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);*/
        
        //top panel
        final JPanel north = new JPanel();
        frame.add(north, BorderLayout.NORTH);
        north.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // score display
        final JPanel scores = new JPanel();
        final JTextField currentScoreDisplay = new JTextField("Current Score: ");
        currentScoreDisplay.setEditable(false);
        currentScoreDisplay.setSize(150, 50);
        this.readHighScores();
        final JTextField highScoreDisplay = 
        		new JTextField("High Score: " + this.getHighScorer() + ", " + this.getHighScore());
        highScoreDisplay.setEditable(false);
        highScoreDisplay.setSize(150, 30);
        scores.setLayout(new BoxLayout(scores, BoxLayout.PAGE_AXIS));
        scores.add(currentScoreDisplay);
        scores.add(highScoreDisplay);
        
        //sad
        c.fill = GridBagConstraints.FIRST_LINE_START;
        //c.gridx = -2;
        //c.gridy = 0;
        north.add(scores, c);
        
        // Reset button
        final JPanel control_panel = new JPanel();
        c.fill = GridBagConstraints.CENTER;
        north.add(control_panel, c);
        
        // lives display
        final JPanel lives = new JPanel();
        lives.add(new JButton("noooooo"));
        c.fill = GridBagConstraints.FIRST_LINE_END;
        //c.gridx = 10;
        //c.gridy = 0;
        north.add(lives, c);
        
        // Main playing area
        final GameCourt court = new GameCourt(userInput, enter, currentScoreDisplay, lives);
        //court.setBackground(Color.BLACK);
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
