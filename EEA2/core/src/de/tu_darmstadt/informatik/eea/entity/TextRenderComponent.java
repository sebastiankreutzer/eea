package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu_darmstadt.informatik.eea.EEA;
import de.tu_darmstadt.informatik.eea.EEAGraphics;

public class TextRenderComponent extends EEARenderComponent {
	
	public static final String ID = "TextRenderComponent";
	
	private String text;
	
	public TextRenderComponent(String text, EEAGraphics eeagraphics){
		super(ID);
		this.text = text;
	}
	
	public void setText(String text){
		this.text = text;
	}

	@Override
	public void render(Batch batch) {
		EEA.getGraphics().drawString(batch, text, owner.getX(), owner.getY() + owner.getHeight());
	}

}
