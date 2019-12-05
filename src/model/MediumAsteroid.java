package model;

import interfaces.Asteroids;
import processing.core.PApplet;

public class MediumAsteroid extends Asteroid implements Asteroids{

	public MediumAsteroid(float x, float y, PApplet w) {
		super(x, y, w);
		this.durchmesser = 100;
	}

}
