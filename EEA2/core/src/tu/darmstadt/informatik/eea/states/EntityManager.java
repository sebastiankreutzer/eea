package tu.darmstadt.informatik.eea.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class EntityManager {
	
	private Stage stage;
	
	public EntityManager() {
		stage = new Stage();
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public void renderEntities() {
		stage.draw();
	}

}
