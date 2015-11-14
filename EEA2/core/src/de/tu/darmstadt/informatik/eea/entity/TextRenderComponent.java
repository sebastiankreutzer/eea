package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu.darmstadt.informatik.eea.EEAGraphics;

public class TextRenderComponent extends RenderComponent {
	
	public static final String ID = "TextRenderComponent";
	
	private EEAGraphics graphics;
	
	private String text;
	
	public TextRenderComponent(String text, EEAGraphics eeagraphics){
		super(ID);
		this.text = text;
		graphics = eeagraphics;
	}

	@Override
	public void render(Batch batch) {
		graphics.drawString(batch, text, owner.getX(), owner.getY() + owner.getHeight());
	}

	@Override
	public void dispose() {
		
	}

}
