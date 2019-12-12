package model;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * Spawns bulletUpgrade, once player collects it, his fireratecooldown decreases
 * @author Raphael Stamm
 *
 */
public class BulletUpgrade extends Asteroid {

	private boolean isCollected;
	private PImage ammoPack;
	
	/**
	 * Ctor for BulletUpgrade
	 * @param x X-pos
	 * @param y Y-pos
	 * @param w PApplet window object
	 */
	public BulletUpgrade(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 50;
		isCollected = false;
		ammoPack = w.loadImage("../images/ammo.png");
		ammoPack.resize((int)durchmesser,(int)durchmesser);
	}
		
	/**
	 * Checks if BulletUpgrade is collected
	 * @param p Player Object
	 * @param a Asteroid Object
	 * @return true / false
	 */
	public boolean isCollected(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
			isCollected = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * draws BulletUpgrade on screen
	 */
	public void draw() {
		w.imageMode(3);
		w.image(ammoPack,x,y);
	}
	
	/**
	 * sets new X- and Y-pos and resets speed
	 */
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}
		
}
