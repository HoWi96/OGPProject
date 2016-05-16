package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.position.CubePosition;

/**
 * A class about the items of the game
 * 
 * @author Holger Willems | 2e bach. ing. OOP
 * @date 16/05/2016
 * @Version 3.0
 * 
 */

/**
 *  
 * 
 * ATTRIBUTES
 * @Invar  The position of each GameObject must be a valid position for any
 *         GameObject.
 *       | isValidPosition(getPosition())
 * @Invar  Each rawMaterial can have its weight as weight.
 *       | canHaveAsWeight(this.getWeight())
 *       
 * ASSOCIATIONS
 * @Invar  The unit of each Item must be a valid unit for any
 *         Item.
 *       | canHaveAs(getUnit())
 * @Invar  The World of each GameObject must be a valid World for any
 *         GameObject.
 *       | isValidWorld(getWorld())    
 */
public abstract class Item {
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
		

	private static final int MAX_WEIGHT = 50;
	private static final int MIN_WEIGHT = 10;


	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
		
	/**
	 * Initialize this new GameObject with given position.
	 * 
	 * @param position
	 * 		the position of this item
	 * @param world
	 * 		the new world of this item
	 *         
	 * @post The game object is not terminated
	 * @post There is no unit assigned to the item
	 * @post There is no unit assigned to the raw item
	 * @post The item gets a random weight between 10 and 50 (inclusive)
	 * 
	 * @effect The position is been set to the given position
	 * @effect the world is been set to the given world
	 */
	public Item(int[] position, World world)throws IllegalArgumentException {
		this.isTerminated = false;
		this.weight = Utils.randInt(MIN_WEIGHT, MAX_WEIGHT);
		world.addItem(this);
		this.setPosition(Utils.getCubeCenter(position));
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Terminates the game object
	 * 
	 * @post The item is terminated
	 * 
	 * @effect if the item has still a world
	 * 		the item will be removed from the world
	 * 
	 * @effect if the item has still a world 
	 * 		the item will be removed from the unit
	 */
	public void terminate(){
		
		if(this.hasWorld())
			this.getWorld().removeItem(this);
		if(this.hasUnit())
			this.getUnit().removeItem(this);
		
		this.isTerminated = true;
	}
	
	/**
	 * Checks whether this unit is terminated
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
	 *       or the item is not attached to a world
	*/
	protected boolean isValidPosition(double[] position) {
		return !this.hasWorld() || (this.getWorld().isValidPosition(Utils.getCubePosition(position))
				&&!this.getWorld().isSolidCube(Utils.getCubePosition(position)));
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
	protected void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Returns a CubePosition representation of the position of this item
	 */
	@Raw
	public CubePosition getCubePosition(){
		int[] cube = Utils.getCubePosition(this.getPosition());
		return new CubePosition(cube);
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
	 *       | result == true;
	*/
	public boolean canHaveAsWorld(World world) {
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
		if (!canHaveAsWorld(world))
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
	public boolean canHaveAsUnit(Unit unit) {
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
	/**
	 * The item will fall until it reaches the ground
	 * 
	 * @param dt
	 * 		the time it will fall
	 * @post
	 * 		the position will be updated till ground is reached
	 */
	protected void falling(double dt){
		double[] position = this.getPosition();
		position[2] = position[2]-3.0*dt;
		this.setPosition(position);
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------WEIGHT---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
		

	/**
	 * Return the weight of this rawMaterial.
	 */
	@Basic @Raw @Immutable
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether this Item can have the given weight as its weight.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return 
	 *       | result == MIN_WEIGHT<=weight && weight<=MAX_WEIGHT 
	 */
	@Raw
	public static boolean isValidWeight(int weight) {
		return (MIN_WEIGHT<=weight && weight<=MAX_WEIGHT);
	}
	
	/**
	 * Variable registering the weight of this rawMaterial.
	 */
	private final int weight;

		

}

