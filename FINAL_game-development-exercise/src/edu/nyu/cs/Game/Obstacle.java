package edu.nyu.cs.Game;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class of the obstacles
 */
public class Obstacle {
    // Instance properties
    private PApplet app; // will hold a reference to the main App object
    private PImage img; // will hold a reference to an image of a cactus
    private String path;
    private float height; // Obstacle height
    private float width; // Obstacle width
    private float x; // Obstacle x position
    private int playerY; // Initial Player y position
    private int playerHeight; // Player height
    private static float obsSpeed = 5; // Obstacle speed
    private boolean onScreen; // Representing if obstacle is on screen
    private int screenWidth; // Width of screen
    private static boolean endGame = false; // Representing if game is over or not
    private float finalSpeed = 15; // Fianl speed of obstacles

    /**
     * Constructor to create an obstacle object using Processing
     * @param screenWidth
     * @param playerY
     * @param playerHeight
     */
    public Obstacle (int screenWidth, int playerY, int playerHeight) {
        this.screenWidth = screenWidth;
        this.x = this.screenWidth + this.width;
        this.playerY = playerY;
        this.playerHeight = playerHeight;
        this.height = getRandomNumber(55,85);
        this.width = getRandomNumber(20, 60);
        this.onScreen = true;
        this.path = null;
    }

    /**
     * Overloaded constructor to create a custom cacti obstacle object 
     * @param app
     * @param screenWidth
     * @param playerY
     * @param playerHeight
     * @param imgFilePath
     */
    public Obstacle (PApplet app, int screenWidth, int playerY, int playerHeight, String imgFilePath) {
        this.app = app;
        this.img = app.loadImage(imgFilePath);
        this.path = imgFilePath;
        this.screenWidth = screenWidth;
        this.x = this.screenWidth + this.width;
        this.playerY = playerY;
        this.playerHeight = playerHeight;
        this.height = playerHeight;
        this.width = 40;
        this.onScreen = true;
    }

    /**
     * Generates random integer for getting the random height and width of obstacles in processing
     * @param min the minimum possible integer
     * @param max the maximum possible integer
     * @return the random integer
     */
    public static int getRandomNumber(int min, int max) {
        int num = (int) ((Math.random() * (max - min)) + min);
        return num;
    }

    /*
     * Changes the X position of the obstacle, and sets/checks the obstacle off the screen
     */
    public void update() {
        if (!endGame) {
            setX(getX() - getObsSpeed());
            if (getX() + getWidth() == 0) {
                setOnScreen(false);
            }
        }
    }
    /*
     * Draws the cactus obstacle
     */
    public void draw(){
        this.app.image(this.img, this.x, this.playerY, this.width, this.height);
    }

    /*
     * Draws the processing obstacle
     */
    public void draw(PApplet app) {
            app.stroke(0,0,0);
            app.strokeWeight(2);
            app.fill(0, 255, 0);
            app.rect(getX(), getPlayerY() + getPlayerHeight() - getHeight(), getWidth(), getHeight());
    }
    
    /*
     * Checks if the player collides with the obstacle, returning a boolean
     */
    public boolean checkCollision(Player player) {  
        float y = getPlayerY() + getPlayerHeight() - getHeight();
        if((player.getY()+player.getHeight()>y)
        &&
        (player.getX()+player.getWidth() > this.getX() 
        &&
        player.getX() < this.getX()+this.getWidth())) {
            return true;
        }
        return false;
    }
    
    // Getters
    public float getHeight() {return this.height;}
    public float getWidth() {return this.width;}
    public float getX() {return this.x;}
    public int getPlayerY() {return this.playerY;}
    public int getPlayerHeight() {return this.playerHeight;}
    public float getObsSpeed() {return obsSpeed;}
    public boolean getOnScreen() {return this.onScreen;}
    public int getScreenWidth() {return this.screenWidth;}
    public boolean getEndGame() {return endGame;}
    public String getPath() {return this.path;}

    // Setters
    public void setHeight(float height) {if(height >= 0) this.height = height;}
    public void setWidth(float width) {if(width >= 0) this.width = width;}
    public void setX(float x) {if(getX() + getWidth() >= 0) this.x = x;}
    public void setObsSpeed(float v) {if(v > 0 && v < finalSpeed) obsSpeed = v;}
    public void setOnScreen(boolean onScreen) {this.onScreen=onScreen;}
    public void setEndGame(boolean b) {endGame = b;}
}
