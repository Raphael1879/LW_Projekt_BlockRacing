package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import processing.core.PApplet;

public class PlayerTest {
	PApplet window;
	Player player;
	
	void setup(){
		System.out.println(player);
		window = new PApplet();
		player = new Player(80,50,100,window);

	}

	@Test
	public void testCtor() {
		System.out.println(player.getX());
		assertEquals(80, player.getX(),0);
	}

}
