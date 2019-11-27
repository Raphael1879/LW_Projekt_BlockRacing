package model;

import processing.core.PApplet;

public class Player extends PApplet {
	
	private float x,y,durchmesser;
	PApplet w;
	private int color = 0xffff0000;
	private int score = 0;
	
	
	
	public Player(float x, float y, float durchmesser, PApplet w) {
		this.x = x;
		this.y = y;
		this.durchmesser = durchmesser;
		this.w = w;
	}


	public void draw() {
		w.strokeWeight(5);
		w.stroke(0x000000);
		w.fill(color);
		w.ellipse(x, y, durchmesser, durchmesser);
	}
	
	public void move() {
		this.x = w.mouseX;
		if(this.x + this.durchmesser/2 > w.width) {
			this.x = w.width - durchmesser/2;
		}
		if(this.x - this.durchmesser/2 < 0) {
			this.x = durchmesser/2;
		}
	}
	
	
	public void scoreUp() {
		w.text(score, 100, 100);
		score++;
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
	
	
	
	
	
}
