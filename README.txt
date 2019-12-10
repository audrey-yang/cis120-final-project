=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: auyang
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections. The collection implemented is a TreeMap that represents the vocabulary. It maps
        the word to the definition. This feature is appropriate because the word and its 
        definition are linked together, and a TreeMap is a good representation for it, as it is fast
        due to its BST nature. 

  2. File I/O. There are two uses for File I/O. The first is importing a .txt file for the 
        vocabulary. This feature is appropriate because the user has total control of what they 
        want in the game. The second is keeping track of the high score. It includes both a Reader
        and a Writer. This feature is appropriate because it can be stored outside of the game
        and is rather permanent that way (instead of it being lost after each round). 

  3. Inheritance. The classes GrayAsteroid and RedAsteroid both extend Asteroid. This feature makes
        sense because they both have intrinsic properties such as position, velocity, and display
        word that are the same. However, they both move differently (RedAsteroid has an acceleration)
        and both look different (take in different image files). Ultimately, they serve different 
        purposes (RedAsteroid will make the player lose a life).
        
  4. Testable Component. The underlying logic of importing a file, setting and getting a high score, 
        and 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
    - Asteroid: Abstract superclass of GrayAsteroid and RedAsteroid. Includes their position, 
        velocity, vocabulary. Includes importing vocabulary method.
    - GrayAsteroid: Extension of the asteroid superclass. Moves at a speed proportional to the level.
    - RedAsteroid: Extension of the asteroid superclass. Moves very fast with an acceleration and 
        its aim is to make the player lose a life.
    - Lives: Displays the number of lives left. Only has a constructor and a draw method.
    - Game: Includes static methods for reading and writing the high score. 
    - GameCourt: Representation of the underlying logic.
    - FileLineIterator: Taken from the TwitterBot HW. A way to read each line from the file.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
    I could not figure out how to make the graphics look better.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    I think there is a pretty good separation of functionality. The private state is pretty well 
    encapsulated. Everything is private except for the game state. If I could refactor, I would 
    try to make everything more succinct (like maaybe not having a Lives class).
    I would also try to make the graphics look better.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  These links were helpful:
  https://stackoverflow.com/questions/21075354/how-can-i-simulate-keypress-in-junit-test
  https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java
  
  The background image is https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwijhrnHtKLmAhVErVkKHbJbCVAQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.jpl.nasa.gov%2Fspaceimages%2Fdetails.php%3Fid%3DPIA23402&psig=AOvVaw05Wpx1ZqNm44v_vAvkXe3i&ust=1575769383027725
  
  Special thanks to Mia Kim for the asteroid graphics!
  
  
