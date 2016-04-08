package de.tu_darmstadt.informatik.eea;

public class EEA {
	
	public static EEA instance = new EEA();
	
	EEAGraphics graphics;
	IResourceManager resourceManager;
	
	public static EEA getInstance() {
		return instance;
	}
	
	public void setGraphics(EEAGraphics graphics) {
		this.graphics = graphics;
	}
	
	public void setResourceManager(IResourceManager resourcesManager) {
		this.resourceManager = resourcesManager;
	}

}
