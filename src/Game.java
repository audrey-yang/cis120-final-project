import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Game implements Runnable {

	private static int highScore;
	private static String highScorer;
	private static final String HIGH_SCORES_FILE = "files/high_scores.txt";
	
	public static int getHighScore() {
		return highScore;
	}
	
	public static String getHighScorer() {
		return highScorer;
	}

	public static void setHighScore(String name, int score) {
		//highScore = score;
		//highScorer = name;
		try {
            FileWriter fw = new FileWriter(HIGH_SCORES_FILE);
            BufferedWriter bf = new BufferedWriter(fw);
            bf.write(name + "\t" + score);
            bf.close();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
	}
	
	public static void readHighScores() {
		FileLineIterator fl = new FileLineIterator(HIGH_SCORES_FILE);
    	if (!fl.hasNext()) highScore = 0;
    	while (fl.hasNext()) {
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
        frame.setLocation(100, 100);
        frame.setResizable(false);

        //user input stuffs
        final JPanel textEnter = new JPanel();
        frame.add(textEnter, BorderLayout.SOUTH);
        final JTextField userInput = new JTextField("");
        userInput.setPreferredSize(new Dimension(100, 20));
        userInput.setEditable(true);
        textEnter.add(userInput);
        final JButton enter = new JButton("Go!");
        textEnter.add(enter);
        //frame.add(enter);
        
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel);
        //frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        
        // Main playing area
        final GameCourt court = new GameCourt(status, userInput, enter);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

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
