package main;


import java.util.ArrayList;

import model.*;
import processing.core.PApplet;

public class Main extends PApplet{
	
	ArrayList<Asteroid> asteroids;
	Player player;
	Asteroid test;
	private int gamestate = 1;
	private int asteroidCount = 5;
	
	

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void settings() {
		size(700,800);
	}
	public void setup() {
		player = new Player(100,700,50,this);
		asteroids = new ArrayList<Asteroid>();
		
		for(int i = 0;i < asteroidCount ; i++) {
			asteroids.add(new Asteroid(random(width),random(-500,height/2),this));
		}
	}


	public void draw() {
		
		//play state
		if(gamestate == 1) {
			drawGameBackground();
			player.move();
			player.draw();
			player.scoreUp();		
			
			// for every Asteroid Object 
			for(Asteroid a:asteroids) {
				a.fall(player);
				a.draw();
				a.hit(player, a);
				if(a.getY()>height+a.getDurchmesser()) {
					a.reset();
				}
			}
			
			if (player.getDead() == true) {
				this.gamestate = 2;
			}
		}
		
		// Gameover state
		if(gamestate == 2) {
			drawEndscreen();
		}		
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
		text("Your Score Was: " + player.getScore(),100,100);
	}
	

}
