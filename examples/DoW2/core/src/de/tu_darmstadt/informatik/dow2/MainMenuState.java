package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGameState;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.QuitAction;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu_darmstadt.informatik.eea.event.MouseEnteredEvent;

public class MainMenuState extends EEAGameState {


	public MainMenuState(EEAGame game) {
		super(game);
	}
	

	@Override
	public void init() {
		
		Entity background = new Entity("menu");
    	background.addComponent(new ImageRenderComponent("menu.png")); // Bildkomponente
    	em.addEntity(background);
    	
    	/* Neues Spiel starten-Enty */
    	Entity new_Game_Entity = new Entity("Neues Spiel starten");
    	new_Game_Entity.setPosition(60, 350);
    	new_Game_Entity.addComponent(new ImageRenderComponent("entry.png"));
    	new_Game_Entity.setSize(330, 100);
    	
    	// Erstelle das Auslöse-Event und die zugehörige Action
    	ANDEvent changeStateEvent = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
    	Action new_Game_Action = new ChangeStateAction(game, DropOfWaterGame.GameplayState, false);
    	changeStateEvent.addAction(new_Game_Action);
    	new_Game_Entity.addComponent(changeStateEvent);
    	
    	// Füge die Entity zum StateBasedEntityManager hinzu
    	em.addEntity(new_Game_Entity);
    	
    	Entity new_Game_Text_Entity = new Entity("NeuesSpielText");
    	new_Game_Text_Entity.setPosition(120, 400);
    	new_Game_Text_Entity.addComponent(new TextRenderComponent("Neues Spiel"));
    	em.addEntity(new_Game_Text_Entity);
    	
    	/* Beenden-Entity */
    	Entity quit_Entity = new Entity("Beenden");
    	quit_Entity.setPosition(60, 230);
    	quit_Entity.addComponent(new ImageRenderComponent("entry.png"));
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
    	quit_Text_Entity.addComponent(new TextRenderComponent("Beenden"));
    	em.addEntity(quit_Text_Entity);
    	
	}

	@Override
	protected void update(float delta) {
	}
}