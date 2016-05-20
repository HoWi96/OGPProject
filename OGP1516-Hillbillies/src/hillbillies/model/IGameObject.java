package hillbillies.model;

import hillbillies.model.helper.CubePosition;

public interface IGameObject {

	/**
	 * Terminates the game object
	 */
	void terminate();

	/**
	 * Return the position of this GameObject.
	 */
	double[] getPosition();

	/**
	 * Returns a CubePosition representation of the position of this game object
	 */
	CubePosition getCubePosition();

	/**
	 * Return the World of this GameObject.
	 */
	World getWorld();

	/**
	 * check whether this gameObject has a world
	 */
	boolean hasWorld();

	/**
	 * Check whether the given World is a valid World for
	 * any GameObject.
	*/
	boolean canHaveAsWorld(World world);

	/**
	 * Set the World of this GameObject to the given World.
	 * 
	 */
	void setWorld(World world) throws IllegalArgumentException;

	/**
	 * Let the time progress for this gameObject
	 */
	void advanceTime(double dt) throws IllegalArgumentException;

	/**
	 * Return the weight of this gameObject
	 */
	int getWeight();

}