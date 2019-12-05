package model;

import processing.core.PApplet;

public class Bullet {
	private float x,y;
	private float durchmesser = 5; // normal: 5
	private float speed = (float) 16; //normal: 16
	PApplet w;

	public Bullet(PApplet w) {
		this.w = w;
		this.x = w.mouseX;
		this.y = w.height - 100;

	}
	
	

	public void shoot() {
		w.stroke(0);
		w.fill(0);
		w.ellipse(x, y, this.durchmesser, this.durchmesser);
		this.y = this.y - speed;
	}



	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}


	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}


	/**
	 * @return the durchmesser
	 */
	public float getDurchmesser() {
		return durchmesser;
	}


	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
}
