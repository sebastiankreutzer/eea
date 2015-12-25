package de.tu.darmstadt.informatik.eea.entity;

/**
 * The component class provides an abstract template for objects that belong to an
 * {@link de.tu.darmstadt.informatik.eea.entity.Entity} instance, its owner.
 * Components can be identified and distinguished by their ID.
 * @author 
 * @version 2.0
 */
public abstract class EEAComponent {

	/** The internal ID of this component. */
	protected String id;

	/** The registered owner {@link de.tu.darmstadt.informatik.eea.entity.Entity} of this component */
	protected Entity owner;

	/**
	 * Creates a new component with the given ID.
	 * @param componentID The ID of this component.
	 */
	public EEAComponent(String componentID) {
		id = componentID;
	}

	/**
	 * Returns the ID of this component.
	 * @return The ID of this component.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the {@link de.tu.darmstadt.informatik.eea.entity.Entity} that is registered as the owner of this component.
	 * @return The owner entity of this component.
	 */
	public Entity getOwnerEntity() {
		return owner;
	}

	/**
	 * Registers a {@link de.tu.darmstadt.informatik.eea.entity.Entity} as the events owner entity.
	 * @param owner The entity that becomes the owner of this component.
	 */
	public void setOwnerEntity(Entity owner) {
		this.owner = owner;
	}

	/**
	 * The update method is called every render cycle and represents the behavior of this component.
	 * @return TODO
	 */
	public abstract boolean update(float delta);

	/**
	 * This method is a hook that is called when the component is added to an @link{de.tu.darmstadt.informatik.eea.entity.Entity},
	 * after the entity is registered as the owner.
	 */
	public void onAddComponent(){

	};

	/**
	 * This method is a hook that is called when the component is removed from an @link{de.tu.darmstadt.informatik.eea.entity.Entity},
	 * before the owner of this component is unregistered.
	 */
	public void onRemoveComponent(){

	};
}
