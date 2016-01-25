package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
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

	private IResourcesManager resourcesManager;

	public MainMenuState(EEAGame game, IResourcesManager resourcesManager) {
		super(game);
		this.resourcesManager = resourcesManager;
		//init();
	}
	
	public void show(){
		super.show();
	}

	public void init() {
		
		Entity background = new Entity("menu");
    	background.addComponent(new ImageRenderComponent("menu.png", resourcesManager)); // Bildkomponente
    	em.addEntity(background);
    	
    	/* Neues Spiel starten-Entitaet */
    	Entity new_Game_Entity = new Entity("Neues Spiel starten");
    	new_Game_Entity.setPosition(100, 300);
    	new_Game_Entity.setScale(0.5f);
    	new_Game_Entity.setDebug(true);
    	new_Game_Entity.addComponent(new ImageRenderComponent("entry.png", resourcesManager));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
    	Action new_Game_Action = new ChangeStateAction(game, LaunchGame.GameplayState);
    	mainEvents.addAction(new_Game_Action);
    	new_Game_Entity.addComponent(mainEvents);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	em.addEntity(new_Game_Entity);
    	
    	Entity new_Game_Text_Entity = new Entity("NeuesSpielText");
    	new_Game_Text_Entity.setPosition(110, 400);
    	new_Game_Text_Entity.addComponent(new TextRenderComponent("Neues Spiel", game.graphics));
    	em.addEntity(new_Game_Text_Entity);
    	
    	/* Beenden-Entitaet */
    	Entity quit_Entity = new Entity("Beenden");
    	quit_Entity.setPosition(100, 100);
    	quit_Entity.setScale(0.5f);
    	quit_Entity.setDebug(true);
    	quit_Entity.addComponent(new ImageRenderComponent("entry.png", resourcesManager));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents_q = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
    	Action quit_Action = new QuitAction();
    	mainEvents_q.addAction(quit_Action);
    	quit_Entity.addComponent(mainEvents_q);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	em.addEntity(quit_Entity);
    	
    	Entity quit_Text_Entity = new Entity("BeendenText");
    	quit_Text_Entity.setPosition(110, 200);
    	quit_Text_Entity.addComponent(new TextRenderComponent("Beenden", game.graphics));
    	em.addEntity(quit_Text_Entity);
	}

	@Override
	protected void update(float delta) {
	}
}