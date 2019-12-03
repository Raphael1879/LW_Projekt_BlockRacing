package model;

import processing.core.PApplet;

public class ScoreAsteroid extends Asteroid {
	
	private boolean isCollected;

	public ScoreAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 30;
		isCollected = false;
	}
	
	
	public boolean hit(Player p, Asteroid a) {
		if(dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
			p.addScore(1000);
			isCollected = true;
			this.reset();
			return true;
		}
		return false;
	}
	

	public void resetAsteroid() {
		this.speed = 0;
		this.y = random(-1000,0-height/2);
		this.x = random(durchmesser,w.width-durchmesser);
		isCollected = false;
		
	}
	
	public void draw() {
		w.fill(255,255,0);
		w.stroke(255,255,0);
		w.ellipse(x, y, durchmesser, durchmesser);
	}

}
