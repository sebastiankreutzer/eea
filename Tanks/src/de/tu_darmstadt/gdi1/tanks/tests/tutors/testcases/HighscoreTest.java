package de.tu_darmstadt.gdi1.tanks.tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.tanks.tests.adapter.TanksTestAdapterExtended1;

public class HighscoreTest {
	
	TanksTestAdapterExtended1 adapter;
	
	@Before
	public void setUp() {
		adapter = new TanksTestAdapterExtended1();
		adapter.resetHighscore();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public final void testCreateHighscoreEntry() {
		
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
		
		adapter.addHighscore("PlayerOne", 23, 123456);
		
		assertEquals("Highscore count should be one after adding an entry", 1, adapter.getHighscoreCount());
		assertEquals("The playername of the highscore entry is incorrect", "PlayerOne", adapter.getNameAtHighscorePosition(0));
		assertEquals("The amount of shots fired of the highscore entry is incorrect", 23, adapter.getShotsFiredAtHighscorePosition(0));
		assertEquals("The time passed of the highscore entry is incorrect", 123456, adapter.getTimePassedAtHighscorePosition(0));	
	}
	
	@Test
	public final void testMaximumHighscoreCount() {
		
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
		
		adapter.addHighscore("PlayerOne", 2, 6824524);
		assertEquals("Highscore count should be 1", 1, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerTwo", 10, 6824524);
		assertEquals("Highscore count should be 2", 2, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerThree", 100, 6824524);
		assertEquals("Highscore count should be 4", 3, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerFour", 54, 6824524);
		assertEquals("Highscore count should be 4", 4, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerFive", 11, 6824524);
		assertEquals("Highscore count should be 5", 5, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerSix", 340, 6824524);
		assertEquals("Highscore count should be 6", 6, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerSeven", 15, 6824524);
		assertEquals("Highscore count should be 7", 7, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerEight", 132, 6824524);
		assertEquals("Highscore count should be 8", 8, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerNine", 9, 6824524);
		assertEquals("Highscore count should be 8", 9, adapter.getHighscoreCount());
		adapter.addHighscore("PlayerTen", 3, 6824524);
		assertEquals("Highscore count should be 9", 10, adapter.getHighscoreCount());
		
		adapter.addHighscore("PlayerEleven", 2, 6824524);
		adapter.addHighscore("PlayerTwelve", 2, 6824524);
		adapter.addHighscore("PlayerThirteen", 2, 6824524);
		adapter.addHighscore("PlayerFourteen", 2, 6824524);
		assertEquals("Only a maximum of 10 highscores should be saved", 10, adapter.getHighscoreCount());
	}
	
	@Test
	public final void testSortHighscoresByShotsFired() {
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
	
		adapter.addHighscore("PlayerSix", 213, 123145);
		adapter.addHighscore("PlayerOne", 1, 123145);
		adapter.addHighscore("PlayerThree", 79, 123145);
		adapter.addHighscore("PlayerFive", 137, 123145);
		adapter.addHighscore("PlayerTwo", 25, 123145);
		adapter.addHighscore("PlayerFour", 100, 123145);
		
		assertEquals("Highscore count should be 6", 6, adapter.getHighscoreCount());
		
		assertEquals("PlayerOne", adapter.getNameAtHighscorePosition(0));
		assertEquals(1, adapter.getShotsFiredAtHighscorePosition(0));
		assertEquals("PlayerTwo", adapter.getNameAtHighscorePosition(1));
		assertEquals(25, adapter.getShotsFiredAtHighscorePosition(1));
		assertEquals("PlayerThree", adapter.getNameAtHighscorePosition(2));
		assertEquals(79, adapter.getShotsFiredAtHighscorePosition(2));
		assertEquals("PlayerFour", adapter.getNameAtHighscorePosition(3));
		assertEquals(100, adapter.getShotsFiredAtHighscorePosition(3));
		assertEquals("PlayerFive", adapter.getNameAtHighscorePosition(4));
		assertEquals(137, adapter.getShotsFiredAtHighscorePosition(4));
		assertEquals("PlayerSix", adapter.getNameAtHighscorePosition(5));
		assertEquals(213, adapter.getShotsFiredAtHighscorePosition(5));
		
		for (int i = 0; i < 6; i++) {
			assertEquals("The amount of time passed is incorrect", 123145, adapter.getTimePassedAtHighscorePosition(i));
		}
	}
	
	@Test
	public final void testSortHighscoresByTimePassed() {
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
	
		
		adapter.addHighscore("PlayerFour", 24, 2045);
		adapter.addHighscore("PlayerOne", 24, 1337);
		adapter.addHighscore("PlayerThree", 24, 1879);
		adapter.addHighscore("PlayerTwo", 24, 1512);
		
		
		assertEquals("Highscore count should be 4", 4, adapter.getHighscoreCount());
		
		assertEquals("PlayerOne", adapter.getNameAtHighscorePosition(0));
		assertEquals(1337, adapter.getTimePassedAtHighscorePosition(0));
		assertEquals("PlayerTwo", adapter.getNameAtHighscorePosition(1));
		assertEquals(1512, adapter.getTimePassedAtHighscorePosition(1));
		assertEquals("PlayerThree", adapter.getNameAtHighscorePosition(2));
		assertEquals(1879, adapter.getTimePassedAtHighscorePosition(2));
		assertEquals("PlayerFour", adapter.getNameAtHighscorePosition(3));
		assertEquals(2045, adapter.getTimePassedAtHighscorePosition(3));
		
		for (int i = 0; i < 4; i++) {
			assertEquals("The amount of time passed is incorrect", 24, adapter.getShotsFiredAtHighscorePosition(i));
		}
	}
	
	@Test
	public final void testSortHighscoresByName() {
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
		
		adapter.addHighscore("PlayerD", 2, 3);
		adapter.addHighscore("PlayerA", 2, 3);
		adapter.addHighscore("PlayerC", 2, 3);
		adapter.addHighscore("PlayerB", 2, 3);
		
		
		assertEquals("Highscore count should be 4", 4, adapter.getHighscoreCount());
		
		assertEquals("PlayerA", adapter.getNameAtHighscorePosition(0));
		assertEquals("PlayerB", adapter.getNameAtHighscorePosition(1));
		assertEquals("PlayerC", adapter.getNameAtHighscorePosition(2));
		assertEquals("PlayerD", adapter.getNameAtHighscorePosition(3));
		
		for (int i = 0; i < 4; i++) {
			assertEquals("The amount of time passed is incorrect", 2, adapter.getShotsFiredAtHighscorePosition(i));
			assertEquals("The amount of time passed is incorrect", 3, adapter.getTimePassedAtHighscorePosition(i));
		}
	}
	
	@Test
	public final void testNullAccess() {
		assertEquals("Highscore count should be zero after reset", 0, adapter.getHighscoreCount());
		
		adapter.addHighscore("PlayerOne", 2, 3);
		
		assertNull("Accessing a non existent position should resturn null", adapter.getNameAtHighscorePosition(-1));
		assertNull("Accessing a non existent position should resturn null", adapter.getNameAtHighscorePosition(1));
		
		assertEquals("Accessing a non existent position should resturn null", -1, adapter.getShotsFiredAtHighscorePosition(-1));
		assertEquals("Accessing a non existent position should resturn null", -1, adapter.getShotsFiredAtHighscorePosition(1));
		
		assertEquals("Accessing a non existent position should resturn null", -1, adapter.getTimePassedAtHighscorePosition(-1));
		assertEquals("Accessing a non existent position should resturn null", -1, adapter.getShotsFiredAtHighscorePosition(1));
		
		assertEquals("PlayerOne", adapter.getNameAtHighscorePosition(0));
		assertEquals(2, adapter.getShotsFiredAtHighscorePosition(0));
		assertEquals(3, adapter.getTimePassedAtHighscorePosition(0));
		
	}
	
	
}
