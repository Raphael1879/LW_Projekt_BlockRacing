package model;

import processing.core.PApplet;
import processing.core.PImage;

public class BulletUpgrade extends Asteroid {

	private boolean isCollected;
	private PImage ammoPack;
	
	public BulletUpgrade(float x, float y, PApplet w) {
		super(x, y, w);
		durchmesser = 50;
		isCollected = false;
		colorRed = 0;
		colorGreen = 0;
		colorBlue = 175;
		ammoPack = w.loadImage("../images/ammo.png");
		ammoPack.resize((int)durchmesser,(int)durchmesser);
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
		w.imageMode(3);
		w.image(ammoPack,x,y);
	}
	
	public void resetAsteroid() {
		this.y = w.random(-1000,0-w.height/2);
		this.x = w.random(durchmesser,w.width-durchmesser);
		speed = 0;
		isCollected = false;
	}
		
}
