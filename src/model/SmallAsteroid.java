  
package model;

import processing.core.PApplet;

public class SmallAsteroid extends Asteroid{

	public SmallAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		this.durchmesser = 50;
		this.hitpoints = 1;
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
	
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		hitpoints = 1;
	}
}