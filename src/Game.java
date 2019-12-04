import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Game implements Runnable {

	public void run() {
		login();
		
	}
	
	public void login() {
		final JFrame frame = new JFrame("Import File");
		frame.setLocation(100, 100);
        frame.setResizable(false);
        
        final JLabel label = new JLabel("Enter the file destination.");
        final JTextField textBox = new JTextField(15);
        final JButton button = new JButton("Go");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	Asteroid.importVocab(textBox.getText());
            }
        });
        
        frame.add(label);
        frame.add(textBox);
        frame.add(button);
        
        final JLabel status = new JLabel("Running...");
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
        
        //if (!Asteroid.getVocab().isEmpty()) return true;
        //else return false;
	}
	
	public void runGame() {
		// Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
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
		Asteroid.importVocab("files/vocab_test.txt");
		SwingUtilities.invokeLater(new Game());
	}
	
}
