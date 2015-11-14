package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;

public class MouseClickedEvent extends EEAEvent {
	
	public static final String ID = "MouseClickedEvent";

	public MouseClickedEvent() {
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		return Gdx.input.isButtonPressed(Buttons.LEFT);
	}

}
