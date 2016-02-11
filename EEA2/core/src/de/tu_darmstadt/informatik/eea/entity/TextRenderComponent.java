package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu_darmstadt.informatik.eea.EEAGraphics;

public class TextRenderComponent extends EEARenderComponent {
	
	public static final String ID = "TextRenderComponent";
	
	private EEAGraphics graphics;
	
	private String text;
	
	public TextRenderComponent(String text, EEAGraphics eeagraphics){
		super(ID);
		this.text = text;
		graphics = eeagraphics;
	}
	
	public void setText(String text){
		this.text = text;
	}

	@Override
	public void render(Batch batch) {
		graphics.drawString(batch, text, owner.getX(), owner.getY() + owner.getHeight());
	}

}
