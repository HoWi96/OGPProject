package hillbillies.model.gameobjects;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;
import hillbillies.model.Utils;
import hillbillies.model.World;

/**
 * @author Holger
 * @version 1.0
 *
 *
 * @invar  Each rawMaterial can have its weight as weight.
 *       | canHaveAsWeight(this.getWeight())
 *       
 * @invar  The unit of each Item must be a valid unit for any
 *         Item.
 *       | canHaveAs(getUnit())
 */
public abstract class RawMaterial extends GameObject {
	
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
	 * @param  position
	 *         The position for this new RawMaterial.
	 * @param  world
	 *         The world for this new RawMaterial.
	 * @effect The rawMaterial is initialized as a GameObject with given values
	 * 			super(position, world)
	 * @post TherawMaterial gets a random weight between 10 and 50 (inclusive)
	 * @post There is no unit assigned to the raw material
	 * 
	 */
	public RawMaterial(double[] position, World world) throws IllegalArgumentException {
		super(position, world);
		this.weight = Utils.randInt(MIN_WEIGHT, MAX_WEIGHT);
		
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	/**
	 * Terminates the raw material
	 * 
	 * @throws IllegalStateException
	 * 		If the raw material is still connected to a unit
	 */
	@Override
	public void terminate() throws IllegalStateException{
		if(this.hasUnit())
			throw new IllegalStateException();
		super.terminate();
	
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
	 *       | result == MIN_WEIGHT<weight && weight<MAX_WEIGHT 
	 */
	@Raw
	public static boolean isValidWeight(int weight) {
		return (MIN_WEIGHT<weight && weight<MAX_WEIGHT);
	}
	
	/**
	 * Variable registering the weight of this rawMaterial.
	 */
	private final int weight;
	
	/** TO BE ADDED TO CLASS HEADING
	 
	 */
	
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
	
	@Override
	public void advanceTime(double dt) throws IllegalArgumentException {
		if(!this.getWorld().isSolidUnder(Utils.getCubePosition(this.getPosition())))
			setFalling(true);
		if(this.isFalling())
			falling(dt);
		
	}
	/**
	 * Indicates whether the unit is falling
	 */
	public boolean isFalling() {
		return isFalling;
	}
	/**
	 * Set the behavior of falling
	 * 
	 * @param falling
	 * 		if the unit is falling
	 * @post
	 * 		this.isFalling() == falling
	 */
	protected void setFalling(boolean falling) {
		this.isFalling = falling;
	}
	
	
	protected void falling(double dt){
		System.out.println("falling raw material");
		//TODO
	}

	/**
	 * boolean indicating whether the raw material is falling
	 */
	private boolean isFalling;
}
