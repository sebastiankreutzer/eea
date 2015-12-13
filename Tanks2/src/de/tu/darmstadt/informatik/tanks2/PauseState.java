package de.tu.darmstadt.informatik.tanks2;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.badlogic.gdx.graphics.Texture;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.QuitAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu.darmstadt.informatik.tanks2.maps.Map;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;
import temp.removeASAP.Tanks;

public class PauseState extends EEAGameState {

	public PauseState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {
		
	}

	@Override
	protected void init() {
		Entity background = new Entity("background");	// Entitaet fuer Hintergrunde
		background.addComponent(new ImageRenderComponent(new Texture("menu.png"))); // Bildkomponente // TODO Load image from AssetManager
		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		em.addEntity(background);
		
		Entity mainMenuText = new Entity("PauseMenuText");
		mainMenuText.setPosition(70, 410);
		mainMenuText.addComponent(new TextRenderComponent("Spiel ist pausiert", game.graphics));
		em.addEntity(mainMenuText);
		
		MenuEntryFactory mef = new MenuEntryFactory(em, game.graphics);
		mef.setDimensions(55, 390, 380, 60);
		
	    //Action new_game = new ChangeStateInitAction(Tanks.GAMEPLAYSTATE);
		mef.prepareMenuEntry("Zurück zum pausierten Spiel", new Texture("entry.png"), new ChangeStateAction(game, LaunchTanks.gameState){
			@Override
			public boolean act(float delta) {
				super.act(delta);
				GameplayLog.getInstance().timer.start();
				return true;
			}
		});
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Spielstand speichern", new Texture("entry.png"), new EEAAction() {
			
			@Override
			public boolean act(float delta) {
				if(!Tanks.debug) {
					JFileChooser fc = new JFileChooser("saves/");
					fc.setFileFilter( new FileFilter() {
						@Override public boolean accept( File f ) {
							return f.isDirectory() || f.getName().toLowerCase().endsWith( ".tanks" );
						}
						@Override public String getDescription() {
							return "Tanks-Spielstaende";
						}
					});
					int state = fc.showSaveDialog(null);

					if( state == JFileChooser.APPROVE_OPTION ) {
						File file = fc.getSelectedFile();
						if(file.getName().endsWith(".tanks")) Map.getInstance().save(file.getName());
						else Map.getInstance().save(file.getPath() +".tanks");

					}
				} else {
					// only for testing
					File file = new File("saves/autosave.tanks");
					Map.getInstance().save(file.getPath());
				}
				return true;
			}
		});
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Zurück zum Hauptmenü", new Texture("entry.png"), new ChangeStateAction(game, LaunchTanks.mainMenu));
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
	}

}
