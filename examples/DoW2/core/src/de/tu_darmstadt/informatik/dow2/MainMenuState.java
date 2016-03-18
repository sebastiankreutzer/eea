package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.QuitAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu_darmstadt.informatik.eea.event.MouseEnteredEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class MainMenuState extends EEAGameState {

	private IResourceManager resourcesManager;

	public MainMenuState(EEAGame game, IResourceManager resourcesManager) {
		super(game);
		this.resourcesManager = resourcesManager;
	}
	
	public void show(){
		super.show();
	}

	public void init() {
		
		Entity background = new Entity("menu");
    	background.addComponent(new ImageRenderComponent("menu.png", resourcesManager)); // Bildkomponente
    	em.addEntity(background);
    	
    	/* Neues Spiel starten-Enty */
    	Entity new_Game_Entity = new Entity("Neues Spiel starten");
    	new_Game_Entity.setPosition(60, 350);
    	new_Game_Entity.addComponent(new ImageRenderComponent("entry.png", resourcesManager));
    	new_Game_Entity.setSize(330, 100);
    	
    	// Erstelle das Auslöse-Event und die zugehörige Action
    	ANDEvent mainEvents = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
    	Action new_Game_Action = new ChangeStateAction(game, GameBootstrapper.GameplayState);
    	mainEvents.addAction(new_Game_Action);
    	new_Game_Entity.addComponent(mainEvents);
    	
    	// Füge die Entity zum StateBasedEntityManager hinzu
    	em.addEntity(new_Game_Entity);
    	
    	Entity new_Game_Text_Entity = new Entity("NeuesSpielText");
    	new_Game_Text_Entity.setPosition(120, 400);
    	new_Game_Text_Entity.addComponent(new TextRenderComponent("Neues Spiel", game.graphics));
    	em.addEntity(new_Game_Text_Entity);
    	
    	/* Beenden-Entity */
    	Entity quit_Entity = new Entity("Beenden");
    	quit_Entity.setPosition(60, 230);
    	quit_Entity.addComponent(new ImageRenderComponent("entry.png", resourcesManager));
    	quit_Entity.setSize(330, 100);
    	
    	// Erstelle das Auslöse-Event und die zugehörige Action
    	ANDEvent mainEvents_q = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
    	Action quit_Action = new QuitAction();
    	mainEvents_q.addAction(quit_Action);
    	quit_Entity.addComponent(mainEvents_q);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	em.addEntity(quit_Entity);
    	
    	Entity quit_Text_Entity = new Entity("BeendenText");
    	quit_Text_Entity.setPosition(120, 280);
    	quit_Text_Entity.addComponent(new TextRenderComponent("Beenden", game.graphics));
    	em.addEntity(quit_Text_Entity);
    	
	}

	@Override
	protected void update(float delta) {
	}
}