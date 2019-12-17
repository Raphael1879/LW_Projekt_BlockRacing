package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import processing.core.PApplet;

/**
 * UnitTests for the class Player
 * @author Raphael Stamm
 *
 */
public class PlayerTest {
	PApplet window;
	Player player;
	@Before
	public void setup() {
		player = new Player(50,80,30,null,null);
	}

	@Test
	public void testCtor() {
		assertEquals(50, player.getX(),0);
		assertEquals(80, player.getY(),0);
		assertEquals(30, player.getDurchmesser(),0);
	}

	 @Test
	 public void testForNegativeNumbers() {
		 if(player.getX() < 0 || player.getY() < 0 || player.getDurchmesser() < 0){
			 fail();
		 } else {
			 assertTrue(true);
		 }
	 }
	 
	 @Test 
	 public void testScoreUp() {
		 player.scoreUp();
		 assertEquals(1,player.getScore(),0);
		 player.addScore(1000);
		 assertEquals(1001,player.getScore(),0);
		 player.scoreUp();
		 assertEquals(1002,player.getScore(),0);
	 }

}
