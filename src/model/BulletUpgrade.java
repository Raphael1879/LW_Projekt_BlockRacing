package model;

import processing.core.PApplet;

public class BulletUpgrade extends Asteroid {

	private boolean isCollected;
	
	public BulletUpgrade(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 50;
		isCollected = false;
		colorRed = 0;
		colorGreen = 0;
		colorBlue = 175;
	}
		
	public boolean isCollected(Player p, Asteroid a) {
		if(w.dist(p.getX(), p.getY(), a.getX(), a.getY()) < p.getDurchmesser()/2 + a.getDurchmesser()/2 && !isCollected) {
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
		w.text("B", x, y+10);
	}
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}
		
}
