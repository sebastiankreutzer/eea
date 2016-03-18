package de.tu_darmstadt.informatik.eea;

public class EEA {
	
	public static EEA instance = new EEA();
	
	private EEAGraphics graphics;
	private IResourceManager resourceManager;
	
	public static EEA getInstance() {
		return instance;
	}
	
	public void setGraphics(EEAGraphics graphics) {
		this.graphics = graphics;
	}
	
	public void setResourceManager(IResourceManager resourcesManager) {
		this.resourceManager = resourcesManager;
	}
	
	public static EEAGraphics getGraphics() {
		return getInstance().graphics;
	}
	
	public static IResourceManager getResourceManager() {
		return getInstance().resourceManager;
	}

}
