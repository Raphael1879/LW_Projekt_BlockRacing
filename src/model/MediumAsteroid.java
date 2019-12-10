package model;

import interfaces.Asteroids;
import processing.core.PApplet;
import processing.core.PImage;

public class MediumAsteroid extends Asteroid{

	private PImage asteroid;
	
	public MediumAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		this.durchmesser = 100;
		this.hitpoints = 2;
		asteroid = w.loadImage("../images/asteroid.png");
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
				hitpoints = 3;
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
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		hitpoints = 2;
		setRandomColor();
	}
}