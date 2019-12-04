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
    	assertTrue(vocab.containsKey("altmodisch"));
    	assertEquals(vocab.get("altmodisch"), "old-fashioned");
    	assertEquals(Asteroid.getVocab().size(), 1);
    }
    
    @Test
    public void testImportAndSplitMultiple() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	assertTrue(vocab.containsKey("beeindrucken"));
    	assertEquals(vocab.get("beeindrucken"), "to impress");
    	assertTrue(vocab.containsKey("begeistert"));
    	assertEquals(vocab.get("begeistert"), "enthusiastic");
    	assertTrue(vocab.containsKey("beurteilen"));
    	assertEquals(vocab.get("beurteilen"), "to assess");
    	assertTrue(vocab.containsKey("das Antragsformular"));
    	assertEquals(vocab.get("das Antragsformular"), "application form");
    	assertEquals(Asteroid.getVocab().size(), 4);
    }
    
    @Test
    public void testGetVocabImmutable() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	vocab.clear();
    	assertFalse(vocab.containsKey("beeindrucken"));
    	assertTrue(Asteroid.getVocab().containsKey("beeindrucken"));
    }
    
    @Test
    public void testGenerateVocab() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	Map<String, String> vocab = Asteroid.getVocab();
    	assertTrue(vocab.containsValue(Asteroid.generateWord()));
    	assertTrue(vocab.containsValue(Asteroid.generateWord()));
    	assertTrue(vocab.containsValue(Asteroid.generateWord()));
    	assertTrue(vocab.containsValue(Asteroid.generateWord()));
    	assertTrue(vocab.containsValue(Asteroid.generateWord()));
    }
    
    /*
	 * 
	 * */

}
