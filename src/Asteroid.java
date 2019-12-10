import java.awt.Graphics;
import java.util.*;

public abstract class Asteroid {
	
	/*
     * Current position of the object 
     *  
     * Coordinates are given by the upper-left hand corner of the object. 
     */
    private int px; 
    private int py;

    /* Size of object, in pixels. */
    private int width;
    private int height;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;
    
    /*
     * Map of vocabulary words taken from file. 
     */
    private static Map<String, String> vocab;
    private String word;
    
    /**
     * CONSTRUCTOR
     */
    public Asteroid(int vy, int vx, int px, int py, int width, int height, int courtWidth,
        int courtHeight) {
        this.vy = vy;
        this.vx = vx;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;

        word = generateWord();
    }
    
    /*
     * VOCAB METHODS
     * */
    
    /*
     * Imports vocab from a .txt file.
     * */
    public static void importVocab(String filePath) {
    	FileLineIterator fl = new FileLineIterator(filePath);
    	vocab = new TreeMap<>();
    	while (fl.hasNext()) {
    		String[] words = fl.next().split("	");
    		if (words.length < 2) {
    			System.out.println("boo " + words[0]);
    			throw new IllegalArgumentException();
    		}
    		vocab.put(words[1], words[0]);
    	}
    }
    
    /*
     * Returns a copy of the vocab map.
     * */
    public static Map<String, String> getVocab() {
    	Map<String, String> copy = new TreeMap<>();
    	copy.putAll(vocab);
    	return copy;
    }
    
    /*
     * Randomly generates a word to put on an asteroid.
     * */
    public static String generateWord() {
    	List<String> keys = new ArrayList<>(vocab.keySet());
    	Random r = new Random();
    	return keys.get(r.nextInt(keys.size()));
    }
    
    /*
     * Helper method to get word from definition.
     * */
    public static String getWordFromDef(String def) {
    	if (vocab.containsKey(def)) return vocab.get(def);
    	return null;
    }

    /* 
     * GETTERS 
     * */
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getWord() {
    	return this.word;
    }
    
    /* 
     * SETTERS
     * */
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }
    
    public void setVy(int vy) {
        this.vy = vy;
    }
    /*
     * MOVEMENT and DRAWING METHODS
     * */
    
    /**
     * Moves the object
     */
    public abstract void move();
    
    /**
     * Default draw method that provides how the object should be drawn in the GUI. This method does
     * not draw anything. Subclass should override this method based on how their object should
     * appear.
     * 
     * @param g The <code>Graphics</code> context used for drawing the object.
     */
    public abstract void draw(Graphics g);
	
}
