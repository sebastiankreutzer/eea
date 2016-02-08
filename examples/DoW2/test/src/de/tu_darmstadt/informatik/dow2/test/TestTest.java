package de.tu_darmstadt.informatik.dow2.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import de.tu_darmstadt.informatik.dow2.LaunchGame;

public class TestTest {
	
	HeadlessApplication app;
	LaunchGame game;
	

	@Before
	public void setUp() throws Exception {
		HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		config.renderInterval = 1.0f/60.0f;
		game = new LaunchGame();
		app = new HeadlessApplication(game, config);
		
	}

	@After
	public void tearDown() throws Exception {
		app.exit();
	}

	@Test
	public void test1() {
		game.setScreen(game.GameplayState);
		assertTrue(true);
		System.out.println("Test1");
	}
	
	@Test
	public void test2() {
		assertTrue(true);
		System.out.println("Test2");
	}

}
