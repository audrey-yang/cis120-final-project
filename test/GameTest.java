import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	
	/*
	 * Import Vocab Tests
	 * */
	
    @Test
    public void testImportAndSplitSingle() {
    	Asteroid.importVocab("files/one_word_test.txt");
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).firstKey(), "altmodisch");
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).get("altmodisch"), 
    			"old-fashioned");
    	assertEquals(Asteroid.getVocab().size(), 1);
    }
    
    @Test
    public void testImportAndSplitMultiple() {
    	Asteroid.importVocab("files/vocab_test.txt");
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).firstKey(), "beeindrucken");
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).get("beeindrucken"), 
    			"to impress");
    	assertTrue(((TreeMap<String, String>) Asteroid.getVocab()).containsKey("begeistert"));
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).get("begeistert"), 
    			"enthusiastic");
    	assertTrue(((TreeMap<String, String>) Asteroid.getVocab()).containsKey("beurteilen"));
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).get("beurteilen"), 
    			"to assess");
    	assertTrue(((TreeMap<String, String>) Asteroid.getVocab()).containsKey("das Antragsformular"));
    	assertEquals(((TreeMap<String, String>) Asteroid.getVocab()).get("das Antragsformular"), 
    			"application form");
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
    
    /*
	 * 
	 * */

}
