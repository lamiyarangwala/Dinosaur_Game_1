package edu.nyu.cs.Game;
import processing.core.PApplet;
import processing.core.PImage;

/**
   * Class representing the player
 */
public class Player {
    // Instance properties
    private PApplet app; // will hold a reference to the main App object
    private PImage img; // will hold a reference to an image of a dinosaur
    private String path;
    private float x; // Top left x position
    private float y; // Top right y position
    private float velY;
    private float gravity;
    private int width; // Player width
    private int height; // Player height
    private boolean jumping; // Representing if player is jumping or not
    private float yInitial; // Initial y position of player
    private static boolean endGame = false; // Representing if game is over or not
    private static double score = 0; // Game score

    /**
     * Constructor to create a Player object at a specific position on the screen
     * @param app a reference to the App object that created this object
     * @param imgFilePath file path of the dinosaur image
     * @param x the x coordinate of this player on the screen
     * @param y the y coordinate of this player on the screen
     * @param width the width
     * @param height the height
     */
    public Player(PApplet app, String imgFilePath, int x, int y, int width, int height) {
        this.app = app;
        this.img = app.loadImage(imgFilePath);
        this.path = imgFilePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.jumping = false;
        this.yInitial = y;
        endGame = false;
        score = 0;
    } 

    /**
     * Makes the player jump by setting Y velocity, gravity, and jumping boolean
     */
    public void jump() {
        if(!endGame) {
            setVelY(160);
            setGravity(50);
            setJumping(true);
        }
    }
    
    /**
     * Moves player by changing the player Y position, Y velocity, gravity, and jumping boolean
     */
    public void move() {
        if (!endGame) {
            if (getJumping() == true) {
                setY(getY() - getVelY() * 1/20); // Change y position
                setVelY(getVelY() - getGravity() * 1/20); // Change velocity
                setGravity(getGravity() + 5); // Change gravity
                //System.out.println(String.format("y: %f, velY: %f, g: %f", getY(), getVelY(), getGravity()));
    
                // If next jump will be below the ground, return to player to original position
                if (getY() - getVelY() * 1/20 > getYInitial()) {
                    setY(getYInitial());
                    setJumping(false);
                }
            }
        }
    }
    
    /**
     * Draws the dinosaur and game score
     * @param app
     */
    public void draw(PApplet app) {
        this.app.image(this.img, getX(), getY(), getWidth(), getHeight());
        // app.fill(255, 255, 255);
        // app.rect(getX(), getY(), getWidth(), getHeight());
        app.textSize(30);
        app.fill(255, 255, 255);
        app.textAlign(app.CENTER);
        app.text("Score: " + String.valueOf((int) score), 800f, 100f);
    }

    // Getters
    public float getX() {return this.x;}
    public float getY() {return this.y;}
    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}
    public float getVelY() {return this.velY;}
    public float getGravity() {return this.gravity;}
    public boolean getJumping() {return this.jumping;}
    public float getYInitial() {return this.yInitial;}
    public boolean getEndGame() {return endGame;}
    public double getScore() {return score;}
    public String getPath() {return this.path;}
    
    // Setters
    public void setY(float y) {if (y>0 && y<1000) this.y = y;}
    public void setVelY(float velY) {if (true) this.velY = velY;}
    public void setGravity(float gravity) {if (gravity > 0) this.gravity = gravity;}
    public void setJumping(boolean jumping) {if (jumping == true || jumping == false) this.jumping = jumping;}
    public void setEndGame(boolean b) {endGame = b;}
    public void setScore(double s) {score = s;}
    public void setPath(String path) {this.path = path;}
}
    
    

