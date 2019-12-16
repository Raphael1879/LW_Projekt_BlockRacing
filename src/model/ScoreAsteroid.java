package model;

import processing.core.PApplet;
import processing.core.PImage;

public class ScoreAsteroid extends Asteroid {
	
	private boolean isCollected;
	private PImage money;
	
	
	/**
	 * Spawns a ScoreAsteroid/moneybag which the player can collect, and is rewarded points for
	 * @param x X-pos
	 * @param y Y-pos
	 * @param w PApplet window Object
	 */
	public ScoreAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 30;
		isCollected = false;
		money = w.loadImage("../images/money.png");
		money.resize((int)durchmesser,(int)durchmesser);
	}
			
	
	/**
	 * detects if a ScoreAsteroid has been collected
	 * @param p Player Object
	 * @param a Asteroid Object
	 * @return True / False
	 */
	public boolean isCollected(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
			p.addScore(1000);
			isCollected = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * draws ScoreAsteroid on screen
	 */
	public void draw() {
		w.imageMode(3);
		w.image(money,x,y);
	}
	
	/**
	 * resets the x-pos and y-pos to new random cords, is used when a ScoreAsteroid falls of the screen
	 */
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}

}