package model;

import interfaces.Asteroids;
import processing.core.PApplet;

public class BigAsteroid extends Asteroid implements Asteroids {

	public BigAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		this.durchmesser = 150;
	}

	
}
