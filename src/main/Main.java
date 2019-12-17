   package main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.*;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * Controller/Main for all the classes in package model
 * @author Raphael Stamm
 *
 */
public class Main extends PApplet{
	
	private ArrayList<Asteroid> asteroids;
	private List<Bullet> bullets;
	private Player player;
	private ScoreAsteroid scoreAsteroid;
	private BulletUpgrade bulletUpgrade;
	private PImage background;
	
	private int gamestate;
	private int asteroidCount;
	
	private int shootTimer;
	private boolean canShoot;
	private int shootCooldown;
	
	private int scoreAsteroidCooldown;
	private int bulletUpgradeCooldown;
	PImage rocket;

	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void settings() {
		size(900,1050);
		fullScreen(1);
	}
	public void setup() {
		frameRate(80);
		
		rocket =  loadImage("../images/rocket.png");
		rocket.resize(80,120);

		gamestate = 0; // 0 = Startscreen, 1 = Play State, 3 = Gameover 
		asteroidCount = 1;
		canShoot = true;
		shootCooldown = 50; //time between shots
		
		scoreAsteroidCooldown = (int) random(500,1500);
		bulletUpgradeCooldown = (int) random(250,1000);
		
		
		background = loadImage("../images/space.png");
		background.resize(width, height);
		
		player = new Player(100,height-100,50,this,rocket);
		asteroids = new ArrayList<Asteroid>();
		bullets = new LinkedList<Bullet>();
		scoreAsteroid = new ScoreAsteroid(random(width),random(-800,-200),this);
		bulletUpgrade = new BulletUpgrade(random(width),random(-800,-200),this);

		// Spawn Asteroids
		for(int i = 0;i < asteroidCount ; i++) {
			addRandomAsteroid();
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
			// if Player is Dead switch to Gameover state
			if (player.getDead()) {
				this.gamestate = 2;
			}
			
			drawGameBackground();
			shootBulletWhenClicked();	
			checkIfBulletsAreOut();
			
			//draw player
			player.move();
			player.draw();
			player.scoreUp();	
			
			// draws and moves every Asteroid and resets them if they go off the screen
			for(Asteroid a:asteroids) {
				a.fall(player);
				a.draw();
				a.hit(player);
				//reset Asteroids that fell of screen
				if(a.getY()>height+a.getDurchmesser()) {
					a.resetAsteroid();
				}
				//remove bullets that hit an Asteroid
				checkBulletHits(a);
			}
			
			// adds Asteroid every 1000 Score
			if(player.getScore() % 1000 == 0) {
				addRandomAsteroid();
				asteroidCount++;
			}
			
			//spawn special Asteroids
			spawnScoreAsteroid();
			spawnBulletUpgrade();

			
		}// Play state end
		
		// Gameover state
		if(gamestate == 2) {
			//draw End screen
			drawEndscreen();
			//if player presses R then reset
			if(keyPressed && key == 'r') {
				resetGame();
			}
			//if player presses e then exit
			if(keyPressed && key == 'e') {
				exit();
			}
		}// Gameover state end	
	}//draw end
	
	
	/**
	 * draws Startscreen
	 */
	private void drawStartscreen() {
		tint(20,20,20);
		imageMode(0);
		image(background,0,0);
		
		fill(39, 45, 110);
		rect(width/2-250,height/2-200,500,320,30);
		
		textSize(30);
		fill(255,255,255);
		textAlign(CENTER);
		text("Welcome to Asteroid Survival",width/2,height/2-100);
		text("Press Any key to start!",width/2,height/2);	
	}

	/**
	 * draws game Background
	 */
	public void drawGameBackground() {
		noTint();
		imageMode(0);
		image(background,0,0);
		fill(255);
		text("Score: " + player.getScore(),150,50);
		text("Cooldown: " + shootCooldown ,150,100);
		text("Asteroids: " + asteroids.size(),150,150);
	}
	
	/**
	 * draws Endscreen
	 */
	public void drawEndscreen() {
		tint(20,20,20);
		imageMode(0);
		image(background,0,0);

		fill(39, 45, 110);
		rect(width/2-200,height/2-300,400,500,30);
		
		textAlign(CENTER);
		textSize(50);
		fill(255,20,20);
		text("GAMEOVER",width/2,height/2-200);
		textSize(30);
		fill(255,255,255);
		text("Your Score Was: " + player.getScore(),width/2,height/2-100);
		text("Press R to restart",width/2,height/2);
		text("Press E to Exit",width/2,height/2+100);
	}
	
	
	/**
	 * adds a random asteroid (Big-,Medium-, SmallAsteroid) to the asteroids ArrayList
	 */
	public void addRandomAsteroid() {
		int r = (int) random(1,4); // random number between 1-3
		switch (r) {
		case 1:
			asteroids.add(new BigAsteroid(random(width),random(-800,-200),this));
			break;
		case 2:
			asteroids.add(new MediumAsteroid(random(width),random(-800,-200),this));
			break;
		case 3:
			asteroids.add(new SmallAsteroid(random(width),random(-800,-200),this));
			break;
		}
	}
	
	/**
	 * Resets the game, is only used when the player presses r while in gameover screen
	 */
	public void resetGame() {
		gamestate = 0;
		player.reset();
		bullets.clear();
		asteroids.clear();
		addRandomAsteroid();
		asteroidCount = 1;
		scoreAsteroid.reset();
		scoreAsteroidCooldown = (int) random(500,1500);
		bulletUpgrade.reset();
		bulletUpgradeCooldown = (int) random(250,1000);
		this.shootCooldown = 50;	
	}
		
	/**
	 * shoots bullets when mouse is clicked
	 */
	public void shootBulletWhenClicked() {
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
			b.draw();
			b.shoot();
		}			
	}
	
	/**
	 * Updates List to only contain Bullets that are currently on screen
	 */
	public void checkIfBulletsAreOut() {
		bullets = bullets
				.stream()
				.filter(b -> b.getY() > -50 - b.getDurchmesser())
				.collect(Collectors.toList());
	}
	
	/**
	 * randomly spawns, draws, and removes ScoreAsteroids
	 */
	public void spawnScoreAsteroid() {
		scoreAsteroidCooldown--;
		// only Spawn when scoreAsteroidCooldown is < 0
		if(scoreAsteroidCooldown < 0) {
			scoreAsteroid.draw();
			scoreAsteroid.fall(player);
			// when scoreAsteroid is collected
			if(scoreAsteroid.isCollected(player, scoreAsteroid)) {
				scoreAsteroid.resetAsteroid();
				scoreAsteroidCooldown = (int) random(500,1500);
			}
			// when scoreAsteroid falls off Screen
			if(scoreAsteroid.getY() > this.height ) {
				scoreAsteroidCooldown = (int) random(500,1500);
				scoreAsteroid.resetAsteroid();
			}
		} 
	}
	
	/**
	 * randomly spawns, draws, and removes BulletUpgrades
	 */
	public void spawnBulletUpgrade() {
		bulletUpgradeCooldown--;
		// only Spawn when bulletUpgradeCooldown is < 0
		if(bulletUpgradeCooldown < 0) {
			bulletUpgrade.draw();
			bulletUpgrade.fall(player);
			// when bulletUpgrade is collected
			if(bulletUpgrade.isCollected(player, bulletUpgrade)) {
				bulletUpgrade.resetAsteroid();
				bulletUpgradeCooldown = (int) random(250,1000);
				this.shootCooldown = this.shootCooldown - 2;				
			}
			// when bulletUpgrade falls off Screen
			if(bulletUpgrade.getY() > this.height ) {
				bulletUpgradeCooldown = (int) random(250,1000);
				bulletUpgrade.resetAsteroid();
			}
		} 
		// When the max cooldown is reached
		if(this.shootCooldown < 0) {
			this.shootCooldown = 0;
		}
	}
	/**
	 * remove bullets that hit an Asteroid	
	 * @param a Asteroid Object
	 */
	public void checkBulletHits(Asteroid a) {		
		bullets = bullets
				.stream()
				.filter(b -> !a.hitBullet(b, a))
				.collect(Collectors.toList());
	}
	
}