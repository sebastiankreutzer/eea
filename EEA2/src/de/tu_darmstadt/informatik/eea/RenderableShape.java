package de.tu_darmstadt.informatik.eea;

import de.tu_darmstadt.informatik.eea.entity.Entity;

public interface RenderableShape {
	void render(Entity owner, ShapeRendererEEA renderer);
}