package model;

import interfaces.AsteroidInterface;
import processing.core.PApplet;


/**
 * Parent class of the 3 AsteroidSize classes, contains all of the basic functions that every Asteroid class has.
 * creates an Asteroid which the player has to dodge
 * @author Raphael Stamm
 *
 */
public abstract class Asteroid implements AsteroidInterface{
	
	PApplet w;
	protected float x;
	protected float y;
	protected float durchmesser;
	protected float speed;
	protected int hitpoints;
	private float speedIncrement;

	
	/**
	 * Ctor for Asteroid
	 * @param x X-pos
	 * @param y Y-pos
	 * @param w PApplet Object
	 */
	public Asteroid(float x, float y,PApplet w) {
		this.x = x;
		this.y = y;
		this.w = w;
		speedIncrement = (float) 0.025;
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
	 */
	public void hit(Player p) {
		if(w.dist(p.getX(), p.getY(), this.getX(), this.getY()) < p.getDurchmesser()/2 + this.getDurchmesser()/2) {
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
	 * Resets the speed of the Asteroid + calls the function resetAsteroid, is only used in the gameoverScreen when the game is restarted
	 */
	public void reset() {
		speedIncrement = (float) 0.05;
		resetAsteroid();
	}
	
	/**
	 * resets the x-pos and y-pos to new random cords, is used when an Asteroid is hit or falls of the screen
	 */
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