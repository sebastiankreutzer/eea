package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CoordinateHelper {
	public static Stage mainStage;
	
	public static int convertToViewSpaceX(int screenSpaceX) {
		int viewSpaceX = (int) (screenSpaceX * mainStage.getWidth() / Gdx.graphics.getWidth());
		return viewSpaceX;
	}
	
	public static int convertToViewSpaceY(int screenY) {
		float stageHeight = mainStage.getHeight();
		int viewSpaceY = (int) (stageHeight - (screenY * stageHeight / Gdx.graphics.getHeight()));
		return viewSpaceY;
	}
}
