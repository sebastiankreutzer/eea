package de.tu.darmstadt.informatik.eea.entity;

public abstract class Component {
	
	/**
	   * the internal ID of this component
	   */
	  protected String id;

	  /**
	   * the internal owner entity of this component
	   */
	  protected Entity owner;

	  /**
	   * Creates a new component with the given ID
	   * 
	   * @param componentID
	   *          the ID of the component
	   */
	  public Component(String componentID) {
	    id = componentID;
	  }

	  /**
	   * returns the ID of this component
	   * 
	   * @return the ID of this component
	   */
	  public String getId() {
	    return id;
	  }

	  /**
	   * returns the entity that "owns" this component
	   * 
	   * @return the entity that "own" this component and is thus associated with
	   *         it, as well as affected by events on the component.
	   */
	  public Entity getOwnerEntity() {
	    return owner;
	  }

	  /**
	   * Assigns the entity that "owns" this component
	   * 
	   * @param owningEntity
	   *          the entity that "owns" the component, (which is associated with
	   *          the component and thus affected by events thereon)
	   */
	  public void setOwnerEntity(Entity owningEntity) {
	    owner = owningEntity;
	  }

	  /**
	   * All components have to provide the update method. This method updates the
	   * component based on GameContainer and the StateBasedGame instance that
	   * represents the current game state
	   * 
	   */
	  public abstract void update(float delta);
	  
	  public void onAddComponent(){
		  
	  };
	  
	  public void onRemoveComponent(){
		  
	  };
}
