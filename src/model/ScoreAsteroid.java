package model;

import processing.core.PApplet;

public class ScoreAsteroid extends Asteroid {
	
	private boolean isCollected;

	public ScoreAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 30;
		isCollected = false;
		colorRed = 215;
		colorGreen = 215;
		colorBlue = 0;
	}
			
	public boolean isCollected(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
			p.addScore(1000);
			isCollected = true;
			return true;
		} else {
			return false;
		}
	}
		
	public void draw() {
		w.stroke(colorRed,colorGreen,colorBlue);
		w.fill(colorRed,colorGreen,colorBlue);
		w.ellipse(x, y, durchmesser, durchmesser);
		w.fill(255);
		w.text("P", x, y+10);
	}
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}
}