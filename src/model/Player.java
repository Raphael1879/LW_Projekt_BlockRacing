package model;

import processing.core.PApplet;

/**
 * Creates The Player
 * @author 183857
 *
 */
public class Player {
	
	private float x,y,durchmesser;
	PApplet w;
	private int color;
	private int score;

	boolean dead = false;
	
	

	public Player(float x, float y, float durchmesser, PApplet w) {
		this.x = x;
		this.y = y;
		this.durchmesser = durchmesser;
		this.w = w;
		color = 0xffff0000;
		score= 0;
	}

	/**
	 * Draws the player on the screen
	 */
	public void draw() {
		w.strokeWeight(5);
		w.stroke(0x000000);
		w.fill(color);
		w.ellipse(x, y, durchmesser, durchmesser);
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
	int scoreInterval = 0;
	public void scoreUp() {
		w.fill(255);
		w.text("Score: " + score, 100, 100);
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