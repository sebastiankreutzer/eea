package de.tu.darmstadt.informatik.eea.event;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.Component;

public abstract class EEAEvent extends Component {
	
	private ArrayList<Action> actions;

	public EEAEvent(String componentID) {
		super(componentID);
		actions = new ArrayList<Action>();
	}
	
	public void addAction(Action action){
		actions.add(action);
	}
	
	public void removeAction(Action action){
		actions.remove(action);
	}
	
	public void clearActions(){
		actions.clear();
	}
	
	public abstract boolean eventTriggered(float delta);
	
	public void update(float delta){
		if (eventTriggered(delta)) {
			for(Action action : actions){
				action.act(delta);
			}
		}
	}

}
