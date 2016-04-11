package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.tu_darmstadt.informatik.eea.entity.Entity;

public interface RenderableShape {
	void render(Entity owner, ShapeRendererEEA renderer);
}