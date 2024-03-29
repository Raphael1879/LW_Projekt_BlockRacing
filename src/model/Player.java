package model;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Creates a rocket, which the player can controll with the mouse
 * @author Raphael Stamm
 *
 */
public class Player {
	
	private float x,y,durchmesser;
	PApplet w;
	private int color;
	private int score;

	boolean dead = false;
	private PImage rocket;
	
	/**
	 * Ctor for Player
	 * @param x X-pos
	 * @param y Y-pos
	 * @param durchmesser Width and height of players hitbox
	 * @param w	PApplet window object
	 */
	public Player(float x, float y, float durchmesser, PApplet w, PImage rocket) {
		this.x = x;
		this.y = y;
		this.durchmesser = durchmesser;
		this.w = w;
		color = 0xffff0000;
		score= 0;
		this.rocket = rocket;
	}	

	/**
	 * Draws the player on the screen
	 */
	public void draw() {
		w.strokeWeight(5);
		w.stroke(0x000000);
		w.fill(color);
		w.noTint();
		w.imageMode(3);
		w.image(rocket,x,y+20);

	}
	
	/**
	 * Moves the Player to the current Mouse X position
	 */
	public void move() {
		this.x = w.mouseX;
		if(this.x + this.durchmesser/2 > w.width) {
			this.x = w.width - durchmesser/2;
		}
		if(this.x - this.durchmesser/2 < 0) {
			this.x = durchmesser/2;
		}
	}
	
	/**
	 * Increases the Score and displays it on the screen
	 */
	public void scoreUp() {
		score++;
	}
	
	/**
	 * Sets the boolean dead
	 * @param t true or false
	 */
	public void dead(boolean t) {
		this.dead = t;
	}
	
	/**
	 * resets the score and player
	 */
	public void reset() {
		this.dead = false;
		this.score = 0;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * @return the durchmesser
	 */
	public float getDurchmesser() {
		return durchmesser;
	}
	
	/**
	 * @return the dead
	 */
	public boolean getDead() {
		return dead;
	}
	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param score the score to set
	 */
	public void addScore(int score) {
		this.score = this.score + score;
	}
}