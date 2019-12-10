import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.JTextField;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	
	/*
	 * Vocab Tests
	 * */
	
    @Test
    public void testImportAndSplitSingle() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	assertTrue(vocab.containsKey("old-fashioned"));
    	assertEquals(vocab.get("old-fashioned"), "altmodisch");
    	assertEquals(Asteroid.getVocab().size(), 1);
    }
    
    @Test
    public void testImportAndSplitMultiple() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	assertTrue(vocab.containsKey("to impress"));
    	assertEquals(vocab.get("to impress"), "beeindrucken");
    	assertTrue(vocab.containsKey("enthusiastic"));
    	assertEquals(vocab.get("enthusiastic"), "begeistert");
    	assertTrue(vocab.containsKey("to assess"));
    	assertEquals(vocab.get("to assess"), "beurteilen");
    	assertTrue(vocab.containsKey("application form"));
    	assertEquals(vocab.get("application form"), "das Antragsformular");
    	assertEquals(Asteroid.getVocab().size(), 4);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testImportInvalid() {
    	Asteroid.importVocab("files/invalid_file.txt");
    }
    
    @Test
    public void testGetVocabImmutable() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	vocab.clear();
    	assertFalse(vocab.containsKey("to impress"));
    	assertTrue(Asteroid.getVocab().containsKey("to impress"));
    }
    
    @Test
    public void testGenerateRandomVocab() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    	assertTrue(vocab.containsKey(Asteroid.generateWord()));
    }
  
    /*
	 * High Score Tests
	 * */
    
    @Test
    public void testSetAndGetHighScore() {
    	Game.setHighScore("Player", 100);
    	Game.readHighScores();
    	assertEquals(Game.getHighScore(), 100);
    	assertEquals(Game.getHighScorer(), "Player");
    }
    
    @Test
    public void testSetMultipleReturnsLatest() {
    	Game.setHighScore("Player1", 100);
    	Game.setHighScore("Player2", 200);
    	Game.setHighScore("Player3", 300);
    	Game.readHighScores();
    	assertEquals(Game.getHighScore(), 300);
    	assertEquals(Game.getHighScorer(), "Player3");
    }
    
    @Test
    public void testSameScoreReset() {
    	Game.setHighScore("Player1", 300);
    	Game.setHighScore("Player2", 300);
    	Game.readHighScores();
    	assertEquals(Game.getHighScore(), 300);
    	assertEquals(Game.getHighScorer(), "Player2");
    }

    
    /*
     * User Input Tests
     * */
    
    @Test
    public void testReset() {
    	GameCourt gc = new GameCourt(new JTextField(), new JTextField(), new JTextField(), 
    			new JTextField());
    	gc.reset();
    	assertTrue(gc.playing);
    	assertEquals(gc.getLives(), 3);
    	assertEquals(gc.getLevel(), 1);
    	assertEquals(gc.getScore(), 0);
    }
    
    @Test
    public void testCorrectAnswer() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	JTextField guess = new JTextField();
    	GameCourt gc = new GameCourt(guess, new JTextField(), new JTextField(), new JTextField());
    	gc.reset();
    	guess.setText("altmodisch");
    	KeyEvent key = new KeyEvent(gc, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
    			KeyEvent.VK_ENTER, 'Z');
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    }
    
    @Test 
    public void testCorrectAnswer2() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	JTextField guess = new JTextField();
    	GameCourt gc = new GameCourt(guess, new JTextField(), new JTextField(), new JTextField());
    	gc.reset();
    	guess.setText("altmodisch");
    	KeyEvent key = new KeyEvent(gc, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
    			KeyEvent.VK_ENTER, 'Z');
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisch");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 200);
    }
    
    @Test
    public void testGuess2Tries() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	JTextField guess = new JTextField();
    	GameCourt gc = new GameCourt(guess, new JTextField(), new JTextField(), new JTextField());
    	gc.reset();
    	guess.setText("altmodisch");
    	KeyEvent key = new KeyEvent(gc, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
    			KeyEvent.VK_ENTER, 'Z');
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisc");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisch");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 190);
    }
    
    @Test
    public void testGuessManyTries() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	JTextField guess = new JTextField();
    	GameCourt gc = new GameCourt(guess, new JTextField(), new JTextField(), new JTextField());
    	gc.reset();
    	guess.setText("altmodisch");
    	KeyEvent key = new KeyEvent(gc, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 
    			KeyEvent.VK_ENTER, 'Z');
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisc");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisc");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisc");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisc");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 100);
    	guess.setText("altmodisch");
    	guess.getKeyListeners()[0].keyReleased(key);
    	assertEquals(gc.getScore(), 160);
    }
    

}
