package interfaces;

import model.Asteroid;
import model.Bullet;
import model.Player;

/**
 * Interface for every Asteroid Class
 * @author Raphael Stamm
 *
 */
public interface Asteroids {
	public void draw();
	public void fall(Player p);
	public void hit(Player p, Asteroid a);
	public boolean hitBullet(Bullet b, Asteroid a);
	public void reset();
	public void resetAsteroid();
	public float getX();
	public float getY();
	public float getDurchmesser();
	public float getSpeed();
}
