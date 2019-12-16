package model;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * object that the player shoots when he holds down m1
 * @author Raphael Stamm
 *
 */
public class Bullet {
	private float x,y;
	private float durchmesser = 5; // normal: 5
	private float speed = (float) 16; //normal: 16
	PApplet w;
	private PImage laser;
	
	/**
	 * Ctor for Bullet
	 * @param w
	 */
	public Bullet(PApplet w) {
		this.w = w;
		this.x = w.mouseX;
		this.y = w.height - 100;
		if(this.x < 25) this.x = 25;
		if(this.x > w.width-25) this.x = w.width-25;
		laser = w.loadImage("../images/laser.png");
		laser.resize((int)durchmesser+10,(int)durchmesser+100);
	}
	
	
	/**
	 * moves bullet upwards
	 */
	public void shoot() {
		this.y = this.y - speed;
	}
	
	/**
	 * draws bullet on screen
	 */
	public void draw() {
		w.noTint();
		w.imageMode(3);
		w.image(laser,x,y+39);
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