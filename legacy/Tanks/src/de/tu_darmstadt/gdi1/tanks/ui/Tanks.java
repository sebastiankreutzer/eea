package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.options.Options;

import eea.engine.entity.StateBasedEntityManager;
 
/**
 * @author Timo Baehr, Bernd Conrad, Alexander Jandousek
 */
public class Tanks extends StateBasedGame {
 
	// Jeder State wird durch einen Integer-Wert gekennzeichnet
    public static final int MAINMENUSTATE		= 0;
    public static final int ONPAUSEMENUSTATE	= 1;
    public static final int GAMEPLAYSTATE		= 2;
    public static final int HIGHSCORESTATE		= 3;
	public static final int OPTIONSTATE			= 4;
	public static final String player1 = "\"PlayerOne\"";
	public static final String player2 = "\"PlayerTwo\"";
	public static final String opponentTank = "\"OpponentTank";
	public static final String healthpack = "\"Healthpack\"";
	public static final String ammopack = "\"Ammopack\"";
	public static boolean debug = false;
	public static final String finish = "null";
 
    public Tanks(boolean debug)
    {
        super("Tanks");
        setDebug(debug);
    }
    
    public static void setDebug(boolean debuging){
    	debug = debuging;
    }
 
    public static void main(String[] args) throws SlickException
    {
//      System.loadLibrary("jawt");
//      System.loadLibrary(System.getProperty("java.home") + "/lib/")
    	// Setze den library Pfad abhaengig vom Betriebssystem
    	if(System.getProperty("os.name").toLowerCase().contains("windows")) {
    		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/native/windows");
    	} 
    	else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
    		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/native/macosx");
    	}
    	else {
    		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/native/" +System.getProperty("os.name").toLowerCase());
    	}
    	System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
    	System.err.println(System.getProperty("os.name") + ": " +System.getProperty("org.lwjgl.librarypath"));
    	// Setze dieses StateBasedGame in einen App Container (oder Fenster) 
        AppGameContainer app = new AppGameContainer(new Tanks(false));
 
        // Lege die Einstellungen des Fensters fest und starte das Fenster
        app.setShowFPS(false);
        //app.setDisplayMode(800, 600, false);
        
        Options.getInstance().toggleSound();
        
        app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
 
        // Fuege dem StateBasedGame die State's hinzu
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new OnPauseMenuState(ONPAUSEMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE));
        this.addState(new HighscoreState(HIGHSCORESTATE));
        this.addState(new OptionsState(OPTIONSTATE));
        
        // Fuege dem StateBasedEntityManager die State's hinzu
        StateBasedEntityManager.getInstance().addState(MAINMENUSTATE);
        StateBasedEntityManager.getInstance().addState(ONPAUSEMENUSTATE);
        StateBasedEntityManager.getInstance().addState(GAMEPLAYSTATE);
        StateBasedEntityManager.getInstance().addState(HIGHSCORESTATE);
        StateBasedEntityManager.getInstance().addState(OPTIONSTATE);
    }
}