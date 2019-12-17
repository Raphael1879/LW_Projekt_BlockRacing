  
package model;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * child-class of Asteroid, 1 of 3 Asteroid Sizes
 * creates an Asteroid which the player has to dodge
 * @author Raphael Stamm
 *
 */
public class SmallAsteroid extends Asteroid{

	private PImage asteroid;
	
	
	/**
	 * Ctor for SmallAsteroid
	 * @param x X-Pos
	 * @param y Y-Pos
	 * @param w PApplet Window
	 */
	public SmallAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		this.durchmesser = 50;
		this.hitpoints = 1;
		asteroid = w.loadImage("../images/smallAsteroid.png");
		asteroid.resize((int)durchmesser,(int)durchmesser);
	}

	/**
	 * Detects if an Asteroid is hit by a Bullet
	 * @param b Bullet Object
	 * @param a Asteroid Objects
	 * @return true if hit , false if not hit
	 */
	public boolean hitBullet(Bullet b, Asteroid a) {
		if(w.dist(b.getX(), b.getY(), a.getX(), a.getY()) < b.getDurchmesser()/2 + a.getDurchmesser()/2) {
			if(hitpoints<=1) {
				resetAsteroid();
				hitpoints = 2;
			}
			hitpoints--;
			return true;
		}
		return false;
	}
	
	/**
	 * Draws Asteroid on the screen
	 */
	public void draw() {
		w.imageMode(3);
		w.image(asteroid, x, y);
	}
	
	
	/**
	 * resets the x-pos and y-pos to new random cords, is used when a ScoreAsteroid falls of the screen
	 */
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		hitpoints = 1;
	}
}