package hillbillies.model.gameobjects;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;
import hillbillies.model.Utils;
import hillbillies.model.World;
/**
 * @author Holger
 * @version 1.0
 */

/** 
 * 
 * @invar  The position of each GameObject must be a valid position for any
 *         GameObject.
 *       | isValidPosition(getPosition())
 * @invar  The World of each GameObject must be a valid World for any
 *         GameObject.
 *       | isValidWorld(getWorld())
 * @invar  The unit of each Item must be a valid unit for any
 *         Item.
 *       | canHaveAs(getUnit())
 */
public abstract class Item {

	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
		
	/**
	 * Initialize this new GameObject with given position.
	 *         
	 * @post The game object is not terminated
	 * @post There is no unit assigned to the item
	 * @post There is no unit assigned to the raw item
	 */
	public Item()throws IllegalArgumentException {
		this.isTerminated = false;
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Terminates the game object
	 * 
	 * @post The game object is terminated
	 * 
	 * @throws IllegalStateException
	 * 			if the GameObject is still connected to a world
	 * @throws IllegalStateException
	 * 		If the raw material is still connected to a unit
	 */
	public void terminate() throws IllegalStateException{
		if(this.hasWorld())
			throw new IllegalStateException("still connected to a world");
		if(this.hasUnit())
			throw new IllegalStateException("still connected to a unit");
		this.isTerminated = true;
	}
	
	/**
	 * Check whether this unit is terminated
	 */
	@Basic @Raw
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * A variable referencing whether this unit is terminated
	 */
	private boolean isTerminated;
	
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------POSITION---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/

	/**
	 * Return the position of this GameObject.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position.clone();
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any GameObject.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       The position is inside the game world and is non solid
	*/
	public boolean isValidPosition(double[] position) {
		return this.getWorld().isValidPosition(Utils.getCubePosition(position))
				&&!this.getWorld().isSolidCube(Utils.getCubePosition(position));
	}
	
	/**
	 * Set the position of this GameObject to the given position.
	 * 
	 * @param  position
	 *         The new position for this GameObject.
	 * @post   The position of this new GameObject is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         GameObject.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this GameObject.
	 */
	private double[] position;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------WORLD--------------------------------------
	 * ------------------NON CONTROLLING CLASS --------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
		

	/**
	 * Return the World of this GameObject.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * check whether this Item has a world
	 */
	public boolean hasWorld(){
		return this.getWorld() != null;
	}
	
	/**
	 * Check whether the given World is a valid World for
	 * any GameObject.
	 *  
	 * @param  World
	 *         The World to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean canHaveAsWorld(World world) {
		return true;
	}
	
	/**
	 * Set the World of this GameObject to the given World.
	 * 
	 * @param  world
	 *         The new World for this GameObject.
	 * @post   The World of this new GameObject is equal to
	 *         the given World.
	 *       | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *         The given World is not a valid World for any
	 *         GameObject.
	 *       | ! isValidWorld(getWorld())
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (! canHaveAsWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}
	
	/**
	 * Variable registering the World of this GameObject.
	 */
	private World world;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------UNIT---------------------------------------
	 * ------------------NON CONTROLLING CLASS--------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Return the unit of this Item.
	 */
	@Basic @Raw
	public Unit getUnit() {
		return this.unit;
	}
	/**
	 * check whether the Item is being carried 
	 */
	public boolean hasUnit(){
		return this.getUnit() != null;
	}
	
	/**
	 * Check whether the given unit is a valid unit for
	 * any Item.
	 *  
	 * @param  unit
	 *         The unit to check.
	 * @return 
	 *       | result == true;
	*/
	public static boolean canHaveAsUnit(Unit unit) {
		return true;
	}
	
	/**
	 * Set the unit of this Item to the given unit.
	 * 
	 * @param  unit
	 *         The new unit for this Item.
	 * @post   The unit of this new Item is equal to
	 *         the given unit.
	 *       | new.getUnit() == unit
	 * @throws IllegalArgumentException
	 *         The given unit is not a valid unit for any
	 *         Item.
	 *       | ! canHaveAsUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) throws IllegalArgumentException {
		if (! canHaveAsUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
	}
	
	/**
	 * Variable registering the unit of this Item.
	 */
	private Unit unit;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------ADVANCE TIME-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Let the time progress for this item
	 * 
	 * @param dt
	 * 		the time to progress
	 * 
	 * @effect if the object is not directly above ground it will falling
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if(!this.getWorld().isSolidUnder(Utils.getCubePosition(this.getPosition())))
			falling(dt);
	}

	protected void falling(double dt){
		double[] position = this.getPosition();
		position[2] = position[2]-3.0*dt;
		this.setPosition(position);
	}
		

}

