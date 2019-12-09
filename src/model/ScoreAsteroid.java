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
		System.out.println(isCollected);
	}
		
	public void draw() {
		w.fill(255,255,0);
		w.stroke(255,255,0);
		w.ellipse(x, y, durchmesser, durchmesser);
	}
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}
	/**
	 * @return the cooldown
	 */
	public boolean isHit() {
		return isHit;
	}
}