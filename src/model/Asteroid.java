package model;

import interfaces.Asteroids;
import processing.core.PApplet;


/**
 * Creates Asteroid Object the PLayer has to dodge
 * @author 183857
 *
 */
public abstract class Asteroid{
	
	PApplet w;
	
	protected float x;
	protected float y;
	protected float durchmesser;
	protected float speed;
	protected int hitpoints;
	private float speedIncrement;

	private int colorRed;
	private int colorGreen;
	
	
	/**
	 * Ctor for Asteroid
	 * @param x
	 * @param y
	 * @param w
	 */
	public Asteroid(float x, float y,PApplet w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.durchmesser = w.random(50,150);
		speedIncrement = (float) 0.025;
		colorRed = (int) w.random(31,189);
		colorGreen = (int) w.random(18,110);
	}
	

	/**
	 * Draws Asteroid on the screen
	 */
	public void draw() {
		w.fill(colorRed,colorGreen,0);
		w.ellipse(x, y, durchmesser, durchmesser);
		w.fill(255);
		w.text(hitpoints, x, y+10);
	}
	
	/**
	 * Makes the Asteroid Fall
	 * @param p To get the Score of the Player
	 */
	public void fall(Player p) {
		this.y = this.y + speed;
		speed = speed + speedIncrement;
		if (p.getScore()% 500 == 0) {
			speedIncrement = (float) (speedIncrement + 0.005);
		}
	}
	
	
	/**
	 * Detects if an Asteroid comes in contact with the player
	 * @param p The Asteroid Object
	 * @param a The Player object
	 * @return 
	 */
	public void hit(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2) {
			p.dead(true);
		}
	}
	
	/**
	 * Detects if an Asteroid is hit by a Bullet
	 * @param b Bullet Object
	 * @param a Asteroid Objects
	 * @return true if hit , false if not hit
	 */
	public boolean hitBullet(Bullet b, Asteroid a) {
		if(w.dist(b.getX(), b.getY(), a.getX(), a.getY()) < b.getDurchmesser()/2 + a.getDurchmesser()/2) {
			a.resetAsteroid();
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the speed of the Asteroid + calls the function resetAsteroid
	 */
	public void reset() {
		speedIncrement = (float) 0.05;
		resetAsteroid();
	}
	
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
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
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}



}