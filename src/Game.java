import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Game implements Runnable {

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

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
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
