package main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.*;
import processing.core.PApplet;

public class Main extends PApplet{
	
	private ArrayList<Asteroid> asteroids;
	private ArrayList<ScoreAsteroid> scoreAsteroids;
	private List<Bullet> bullets;
	private Player player;
	private Asteroid test;
	private ScoreAsteroid scoreAsteroid;
	private int gamestate = 0; // 0 = Startscreen, 1 = Play State, 3 = Gameover
	private int asteroidCount = 10;
	
	
	private boolean canShoot = true;
	int shootTimer = 0; //dont touch
	int shootCooldown = 30; //time between shots
	int scoreAsteroidCooldown = (int) random(500,1500);
	

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void settings() {
		size(700,800);
	}
	public void setup() {
		player = new Player(100,700,50,this);
		asteroids = new ArrayList<Asteroid>();
		bullets = new LinkedList<Bullet>();
		scoreAsteroid = new ScoreAsteroid(random(width),random(-800,-200),this);

		
		for(int i = 0;i < asteroidCount ; i++) {
			asteroids.add(new Asteroid(random(width),random(-800,-200),this));
			
		}
	}


	public void draw() {
		//startscreen state
		if(gamestate == 0) {
			drawStartscreen();
			
			if(keyPressed) {
				gamestate = 1;
			}
				
			
		}// startscreenstate end
		
		
		//play state
		if(gamestate == 1) {
			drawGameBackground();
			
			// if Player is Dead switch to Gameover state
			if (player.getDead() == true) {
				this.gamestate = 2;
			}
			
			//If Mouse is clicked then shoot
			if(mousePressed && canShoot == true) {
				bullets.add(new Bullet(this));
				canShoot = false;
			}
			
			//Bullet Cooldown Timer
			if (!canShoot) {
				shootTimer++;
				if(shootTimer > shootCooldown) {
					canShoot=true;
					shootTimer = 0;
				}
			}
			// Moves every Bullet
			for(Bullet b: bullets) {
				b.shoot();
			}
			
			 // Updates List to only contain Bullets that are in the Game Window
			bullets = bullets.stream().filter(b -> b.getY() > 0 - b.getDurchmesser()).collect(Collectors.toList());
			
			
			//draw player
			player.move();
			player.draw();
			player.scoreUp();		
			
			
			
			// draws and moves every Asteroid and resets them if they go off the screen
			for(Asteroid a:asteroids) {
				a.fall(player);
				a.draw();
				a.hit(player, a);
				if(a.getY()>height+a.getDurchmesser()) {
					a.resetAsteroid();
				}
				
				for(Bullet b:bullets) {
					if(a.hitBullet(b, a)) {
						a.resetAsteroid();
						
					}
				}
				

			}
			
			//Spawn Score Asteroid
			scoreAsteroidCooldown--;
			if(scoreAsteroidCooldown < 0) {
				scoreAsteroid.draw();
				scoreAsteroid.fall(player);
				scoreAsteroid.hit(player, scoreAsteroid);
				if(scoreAsteroid.isHit() == true) {
					scoreAsteroid.reset();
					scoreAsteroidCooldown = (int) random(500,1500);
				}
				if(scoreAsteroid.getY() > this.height ) {
					scoreAsteroidCooldown = (int) random(500,1500);
					scoreAsteroid.reset();
				}
			} 

			
			
		}// Play state end
		
		// Gameover state
		if(gamestate == 2) {
			//draw End screen
			drawEndscreen();
			//if player presses R the reset
			if(keyPressed && key == 'r') {
				player.reset();
				for(Asteroid a:asteroids) {
					a.reset();
				}
				gamestate = 0;
				bullets.clear();
				scoreAsteroid.reset();
			}
			//if player presses e then exit
			if(keyPressed && key == 'e') {
				exit();
			}
		}// Gameover state end	
	}
	
	
	
	private void drawStartscreen() {
		background(30);
		textSize(30);
		fill(255,255,255);
		textAlign(CENTER);
		text("Press Any Key to Start!",250,250);
		
	}

	public void drawGameBackground() {
		background(0x4e4f57);

		stroke(0x00000000);		
		line(0,696, width, 696);
		line(0,704, width, 704);
		
		stroke(0xffff0000);
		line(0,700, width, 700);
	}
	
	public void drawEndscreen() {
		background(30);
		textSize(30);
		fill(255,255,255);
		textAlign(CENTER);
		text("Your Score Was: " + player.getScore(),300,100);
		text("Press R to restart",300,200);
		text("Press E to Exit",300,300);
	}
	

}
