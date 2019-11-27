package main;


import model.Player;
import processing.core.PApplet;

public class Main extends PApplet{
	
	Player player;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void settings() {
		size(500,800);
	}
	public void setup() {
		player = new Player(100,700,50,this);
		
	}
	public void draw() {
		drawBackground();
		player.move();
		player.draw();
		player.scoreUp();
	}
	
	
	
	public void drawBackground() {
		
		background(0x4e4f57);

		stroke(0x00000000);		
		line(0,696, width, 696);
		line(0,704, width, 704);
		
		stroke(0xffff0000);
		line(0,700, width, 700);
		

	}
}
