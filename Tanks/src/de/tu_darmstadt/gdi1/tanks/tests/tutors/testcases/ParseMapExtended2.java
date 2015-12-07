package de.tu_darmstadt.gdi1.tanks.tests.tutors.testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tu_darmstadt.gdi1.tanks.tests.adapter.TanksTestAdapterExtended2;

public class ParseMapExtended2 {

	TanksTestAdapterExtended2  adapter;
	
	String 	map01 = "testmaps/extended2/correct01e2",
			map02 = "testmaps/extended2/correct02e2",
			map03 = "testmaps/extended2/correct03e2",
			map04 = "testmaps/minimal/correct01";
	
	String stringRepresentation01 = "Map \"/assets/sandTexture.jpg\" \"correct01e2\" \"null\" 0 0 0\n" +
									"Tank \"PlayerOne\" 1000 1000 10 10 3 3 30 5 0 10 300 200\n" +
									"Tank \"OpponentTank0\" 30 30 1 1 0 0 1 5 270 10 100 200\n" +
									"Scattershot 10 5 231 10 112 372\n";
	String stringRepresentation02 = "Map \"/assets/sandTexture.jpg\" \"correct02e2\" \"null\" 0 0 0\n" +
								    "Tank \"PlayerOne\" 1000 1000 10 10 3 3 30 5 0 10 300 200\n" +
								    "Tank \"OpponentTank0\" 30 30 1 1 0 0 1 5 270 10 100 200\n" +
								    "Tower 55 30 12 3 14 50 113 12 175 399\n";
	String stringRepresentation03 = "Map \"/assets/sandTexture.jpg\" \"correct03e2\" \"null\" 0 0 0\n" +
									"Tank \"PlayerOne\" 1000 1000 10 10 3 3 30 5 0 10 300 200\n" +
									"Tank \"OpponentTank0\" 30 30 1 1 0 0 1 5 270 10 100 200\n" +
									"Pickup \"Healthpack\" 13 212 13 430 276\n" +
									"Pickup \"Ammopack\" 55 13 15 9 188\n";
	String stringRepresentation04 = "Map \"/assets/sandTexture.jpg\" \"correct01\" \"null\" 0 0 0\n" +
									"Tank \"PlayerOne\" 1000 1000 10 10 3 3 30 5 0 10 300 200\n" +
									"Tank \"OpponentTank0\" 30 30 1 1 0 0 1 5 270 10 100 200\n";
	
	@Before
	public void setUp() {
		adapter = new TanksTestAdapterExtended2();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public final void testLoadMapFromFileScattershotExtension() {
		adapter.loadMapFromFile(new File(map01));
		assertTrue("A correct map was detected as incorrect (scattershot extension): " + map01, adapter.isCorrectMap());
	}
	
	@Test
	public final void testLoadMapFromFileTowerExtension() {
		adapter.loadMapFromFile(new File(map02));
		assertTrue("A correct map was detected as incorrect (tower extension): " + map02, adapter.isCorrectMap());
	}
	
	@Test
	public final void testLoadMapFromFilePickupExtension() {
		adapter.loadMapFromFile(new File(map03));
		assertTrue("A correct map was detected as incorrect (pickup extension): " + map03, adapter.isCorrectMap());
	}
	
	@Test
	public final void testScattershotEntitiy() {
		adapter.loadMapFromFile(new File(map01));
		assertTrue("A correct map was detected as incorrect (scattershot extension): " + map01, adapter.isCorrectMap());
		assertEquals("String representation of " + map01 + " is wrong" , stringRepresentation01, adapter.getStringRepresentationOfMap());
		
		assertEquals("Incorrect scattershot count", 1, adapter.getScattershotCount());
		
		assertEquals("Incorrect time value of scattershot at position 0", 10, adapter.getScattershotTime(0));
		assertEquals("Incorrect strength value of scattershot at position 0", 5, adapter.getScattershotStrength(0));
		assertEquals("Incorrect rotation value of scattershot at position 0", 231, adapter.getScattershotRotation(0));
		assertEquals("Incorrect scale value of scattershot at position 0", 10, adapter.getScattershotScale(0));
		assertEquals("Incorrect x position value of scattershot at position 0", 112, adapter.getScattershotXPosition(0));
		assertEquals("Incorrect y position value of scattershot at position 0", 372, adapter.getScattershotYPosition(0));
	}
	
	@Test
	public final void testShotScattershot() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map04));
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation04, adapter.getStringRepresentationOfMap());
		assertEquals("Scattershot entitiy count should be 0", 0, adapter.getScattershotCount());
		adapter.handleKeyPressL();
		assertEquals("Scattershot entitiy count should be 1", 1, adapter.getScattershotCount());
		adapter.stopGame();
	}
	
	@Test
	public final void testTowerEntitiy() {
		adapter.loadMapFromFile(new File(map02));
		assertTrue("A correct map was detected as incorrect (tower extension): " + map02, adapter.isCorrectMap());
		assertEquals("String representation of " + map02 + " is wrong" , stringRepresentation02, adapter.getStringRepresentationOfMap());
		
		assertEquals("Incorrect tower count", 1, adapter.getTowerCount());
		
		assertEquals("Incorrect maximum life value of tower at position 0", 55, adapter.getTowerMaximumLife(0));
		assertEquals("Incorrect actual life value of tower at position 0", 30, adapter.getTowerActualLife(0));
		assertEquals("Incorrect maximum shot ammo value of tower at position 0", 12, adapter.getTowerMaximumShotAmmo(0));
		assertEquals("Incorrect actual shot ammo value of tower at position 0", 3, adapter.getTowerActualShotAmmo(0));
		assertEquals("Incorrect strength value of tower at position 0", 14, adapter.getTowerStrength(0));
		assertEquals("Incorrect speed value of tower at position 0", 50, adapter.getTowerSpeed(0));
		assertEquals("Incorrect rotation value of tower at position 0", 113, adapter.getTowerRotation(0));
		assertEquals("Incorrect scale value of tower at position 0", 12, adapter.getTowerScale(0));
		assertEquals("Incorrect x position value of tower at position 0", 175, adapter.getTowerXPosition(0));
		assertEquals("Incorrect y position value of tower at position 0", 399, adapter.getTowerYPosition(0));
	}
	
	@Test
	public final void testPickupEntitiy() {
		adapter.loadMapFromFile(new File(map03));
		assertTrue("A correct map was detected as incorrect (tower extension): " + map03, adapter.isCorrectMap());
		assertEquals("String representation of " + map03 + " is wrong" , stringRepresentation03, adapter.getStringRepresentationOfMap());

		assertEquals("Incorrect pickup count", 2, adapter.getPickupCount());
		// Healtpack pickup
		assertEquals("Incorrect type value of pickup at position 0", "Healthpack", adapter.getPickupType(0));
		assertEquals("Incorrect strength value of pickup at position 0", 13, adapter.getPickupStrength(0));
		assertEquals("Incorrect rotation value of pickup at position 0", 212, adapter.getPickupRotation(0));
		assertEquals("Incorrect scale value of pickup at position 0", 13, adapter.getPickupScale(0));
		assertEquals("Incorrect x position value of pickup at position 0", 430, adapter.getPickupXPosition(0));
		assertEquals("Incorrect y position value of pickup at position 0", 276, adapter.getPickupYPosition(0));
		// Ammopack pickup
		assertEquals("Incorrect type value of pickup at position 0", "Ammopack", adapter.getPickupType(1));
		assertEquals("Incorrect strength value of pickup at position 0", 55, adapter.getPickupStrength(1));
		assertEquals("Incorrect rotation value of pickup at position 0", 13, adapter.getPickupRotation(1));
		assertEquals("Incorrect scale value of pickup at position 0", 15, adapter.getPickupScale(1));
		assertEquals("Incorrect x position value of pickup at position 0", 9, adapter.getPickupXPosition(1));
		assertEquals("Incorrect y position value of pickup at position 0", 188, adapter.getPickupYPosition(1));
	}
	
}
