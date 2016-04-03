package hillbillies.model.gameobjects;

import be.kuleuven.cs.som.annotate.*;
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
 */
public abstract class RawMaterial extends Item {
	
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
	 * @post The raw material gets a random weight between 10 and 50 (inclusive)
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

}
