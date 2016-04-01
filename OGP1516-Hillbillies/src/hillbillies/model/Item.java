package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public abstract class Item {
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR--------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	/**
	 * Initialize this new Item with given position.
	 *
	 *
	 * @param world
	 * 			The world for this new Item.
	 * @param  position
	 *         The position for this new Item.
	 *  
	 * @effect The position of this new Item is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 *       
	 * @post The world of this item is set to the given world
	 * @post The weight of this item is set to a random weight
	 * 		between 10 and 50
	 */
	public Item(World world, double[] position) throws IllegalArgumentException{
		this.world = world;
		this.setPosition(position);
		this.weight = Utils.randInt(10,50);
	}
	
	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The position of each Item must be a valid position for any
	 *         Item.
	 *       | isValidPosition(getPosition())
	 */

/**
 * Return the position of this Item.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position;
}

/**
 * Check whether the given position is a valid position for
 * any Item.
 *  
 * @param  position
 *         The position to check.
 * @return 
 *       | result == 
*/
public static boolean isValidPosition(double[] position) {
	return false;
}

/**
 * Set the position of this Item to the given position.
 * 
 * @param  position
 *         The new position for this Item.
 * @post   The position of this new Item is equal to
 *         the given position.
 *       | new.getPosition() == position
 * @throws IllegalArgumentException
 *         The given position is not a valid position for any
 *         Item.
 *       | ! isValidPosition(getPosition())
 */
@Raw
public void setPosition(double[] position) 
		throws IllegalArgumentException {
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	this.position = position;
}

/**
 * Variable registering the position of this Item.
 */
private double[] position;

/**
 * Return the weight of this item.
 */
@Basic @Raw @Immutable
public int getWeight() {
	return this.weight;
}


/**
 * Variable registering the weight of this item.
 */
private final int weight;


/**
 * Return the world of this item.
 */
@Basic @Raw @Immutable
public World getWorld() {
	return this.world;
}

/**
 * Check whether this item can have the given world as its world.
 *  
 * @param  world
 *         The world to check.
 * @return 
 *       | result == 
*/
@Raw
public boolean canHaveAsWorld(World world) {
	return false;
}

/**
 * Variable registering the world of this item.
 */
private final World world;

/** TO BE ADDED TO CLASS HEADING
 * @invar  The carrier of each item must be a valid carrier for any
 *         item.
 *       | isValidCarrier(getCarrier())
 */

/**
 * Return the carrier of this item.
 */
@Basic @Raw
public Unit getCarrier() {
	return this.unit;
}

/**
 * Check whether the given carrier is a valid carrier for
 * any item.
 *  
 * @param  carrier
 *         The carrier to check.
 * @return 
 *       | result == 
*/
public static boolean isValidCarrier(Unit unit) {
	return unit != null;
}

/**
 * Set the carrier of this item to the given carrier.
 * 
 * @param  unit
 *         The new carrier for this item.
 * @post   The carrier of this new item is equal to
 *         the given carrier.
 *       | new.getCarrier() == unit
 * @throws IllegalArgumentException
 *         The given carrier is not a valid carrier for any
 *         item.
 *       | ! isValidCarrier(getCarrier())
 */
@Raw
public void setCarrier(Unit unit) 
		throws IllegalArgumentException {
	if (! isValidCarrier(unit))
		throw new IllegalArgumentException();
	this.unit = unit;
}

/**
 * Variable registering the carrier of this item.
 */
private Unit unit;

}
