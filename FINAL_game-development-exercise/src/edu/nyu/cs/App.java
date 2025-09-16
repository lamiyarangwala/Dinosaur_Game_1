package edu.nyu.cs;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import edu.nyu.cs.Game.*;
import processing.core.*;

/**
   * The program runs a game modeled after the Google Dinosaur Game
   * The program implements its own version of an obstacle, and also uses online images for the dinosaur and obstacles as well
  *
  * @author Owen Zhang & Lamiya Rangwala
  * @version Final
 */
public class App extends PApplet {

    // Properties
    int playerX = 100; // intial x position of Player
    int playerY = 520; // intial y position of Player
    int playerWidth = 50; // width of player
    int playerHeight = 60; // height of player
    int screenWidth = 1000; // screen width
    int screenHeight = 800; // screen height
    int v1 = 203; // background v1
    int v2 = 195; // background v2
    int v3 = 227; // background v3
    float velocityIncrement = 0.001f; // increment of obstacle velocity as the game goes on
    private ArrayList<Player> players; // will hold an ArrayList of Players objects

    // Paths
    String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
    String dinosaur_path = Paths.get(cwd, "images", "dinosaur.png").toString(); 

    // Objects
    Background background = new Background(0, playerY + playerHeight, screenWidth, playerY + playerHeight);
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    /**
     * A method that can be used to modify settings of the window, such as set its size.
     * This method shouldn't really be used for anything else.  
     * Use the setup() method for most other tasks to perform when the program first runs.
     */
    public void settings() {
        this.setSize(screenWidth, screenHeight);
}

	/**
	 * This method will be automatically called by Processing when the program runs.
	 */
    public void setup() {

        // Makes arraylist of player objects and instantiates a player
        players = new ArrayList<Player>();
        this.players.add(new Player(this, dinosaur_path, playerX, playerY, playerWidth, playerHeight));

        // Adds background
        this.background(v1, v2, v3);
        // Gets first player from players arraylist
        Player player = this.players.get(0);
        player.draw(this);
        }

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
	 */
    public void draw() {
        Player player = this.players.get(0);
        this.background(v1, v2, v3);
        background.draw(this);
        player.move();
        player.draw(this);

        // Instantiates random new obstacles
        if(random(1) < 0.8 && frameCount % 60 == 0) {
            String path = Paths.get(cwd, "images", "cactus.png").toString(); 
            if(random(1) < 0.5){ 
            obstacles.add(new Obstacle(this, screenWidth, playerY, playerHeight, path));
            } else{
                obstacles.add(new Obstacle(screenWidth, playerY, playerHeight));
            }
        }
        
        // Loops through arraylist of obstacles
        // Trying to remove objects from the class that are no longer on the screen, currently not fully implemented
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            if (obstacle.getOnScreen()) { // If obstacle is on screen
                obstacle.update();
                if (obstacle.getPath()==null){
                    obstacle.draw(this); // draw regular processing shapes
                }
                else{
                obstacle.draw(); // draw custom obstacle
                }
                if (!obstacle.getEndGame()) { // Game still ongoing
                    obstacle.setObsSpeed(obstacle.getObsSpeed() + velocityIncrement); // Increases obstacles' speed by an increment every frame
                    if (frameCount % 6 == 0) { 
                        player.setScore(player.getScore() + 1); // Updates game score
                    }
                    //System.out.println("Obstacle speed: " + obstacle.getObsSpeed());
                    if (obstacle.checkCollision(player)) { // If player collides with an obstacle, set endgame boolean as true
                        obstacle.setEndGame(true);
                        player.setEndGame(true);
                    }
                } else { // Displays when game is over
                    textSize(100);
                    textAlign(CENTER);
                    fill(255, 255, 255);
                    text("GAME OVER!", screenWidth / 2, screenHeight /2);
                }
            } else {
                iterator.remove();
                // System.out.println("removed");
            }
        }
        // System.out.println(obstacles.size());
        }

	/**
	 * This method is automatically called by Processing every time the user presses a key while viewing the map.
	 */
    public void keyPressed() {
        //System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
        Player player = this.players.get(0);
        switch (this.key) {
            case ' ': // Press space to jump
                if (player.getJumping() == false) {
                    player.jump();
                    player.move();
                break;
            }
            // case 'r':
            //     player = new Player(playerX, playerY, playerWidth, playerHeight);
            //     background = new Background(0, playerY + playerHeight, screenWidth, playerY + playerHeight);
            //     obstacles = new ArrayList<Obstacle>();
            //     // player.setEndGame(false);
            //     // for ()
            //     // obstacle.setEndGame(true);
            //     break;     
        }
    }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * @param args An array of any command-line arguments.
   */
    public static void main(String[] args) {
        // Processing requires us to pass our full package + class name to its main method.
        PApplet.main("edu.nyu.cs.App");
    }
}
