package model;

import processing.core.PApplet;

public class Bullet {
	private float x,y;
	private float durchmesser = 5;
	private float speed = 16;
	PApplet w;

	public Bullet(PApplet w) {
		this.w = w;
		this.x = w.mouseX;
		this.y = 700;

	}
	
	




	public void shoot() {
		w.stroke(255);
		w.ellipse(x, y, this.durchmesser, this.durchmesser);
		move();
	}

	
	public void move() {
		this.y = this.y - speed;
	}
	
	
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
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
