import static org.junit.Assert.*;

import java.util.*;
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
    
    @Test
    public void testGetVocabImmutable() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	vocab.clear();
    	assertFalse(vocab.containsKey("to impress"));
    	assertTrue(Asteroid.getVocab().containsKey("to impress"));
    }
    
    @Test
    public void testGenerateVocab() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
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
    	Game game = new Game();
    	game.setHighScore("Player", 100);
    	game.readHighScores();
    	assertEquals(game.getHighScore(), 100);
    	assertEquals(game.getHighScorer(), "Player");
    }
    
    @Test
    public void testSetMultipleReturnsLatest() {
    	Game game = new Game();
    	game.setHighScore("Player1", 100);
    	game.setHighScore("Player2", 200);
    	game.setHighScore("Player3", 300);
    	game.readHighScores();
    	assertEquals(game.getHighScore(), 300);
    	assertEquals(game.getHighScorer(), "Player3");
    }
    
    

}
