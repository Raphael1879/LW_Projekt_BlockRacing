package model;

import processing.core.PApplet;

public class ScoreAsteroid extends Asteroid {
	
	private boolean isCollected;
	private boolean isHit;

	public ScoreAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 30;
		isCollected = false;
	}
		
	public void hit(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
			p.addScore(1000);
			isCollected = true;
			isHit = true;
		} else {
			isHit = false;
		}
	}
		
	public void draw() {
		w.fill(255,255,0);
		w.stroke(255,255,0);
		w.ellipse(x, y, durchmesser, durchmesser);
	}
	
	/**
	 * @return the cooldown
	 */
	public boolean isHit() {
		return isHit;
	}
}