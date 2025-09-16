package edu.nyu.cs.Game;
import processing.core.PApplet;

/**
 * Claass of the background, making a line for the ground
 */
public class Background {
    // Instance properties
    private float x1;
    private float y1;
    private float x2;
    private float y2;

    public Background(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(PApplet app) {
        app.line(this.x1, this.y1, this.x2, this.y2);
    }
}
