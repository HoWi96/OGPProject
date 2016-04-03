package hillbillies.model.items.rawmaterials;

import hillbillies.model.World;
import hillbillies.model.items.Item;

/**
 * @author Holger
 * @version 1.0
 *
 *
 *       
 */
public abstract class RawMaterial extends Item {
	

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
	 *         
	 * @effect The rawMaterial is initialized as a GameObject
	 *         
	 * @effect The position of this new raw material is set to
	 *         the given position.
	 * @effect The world of of this new raw material is set to
	 *         the given world.
	 * 
	 * 
	 */
	public RawMaterial(double[] position, World world) throws IllegalArgumentException {
		super();
		this.setPosition(position);
		world.addItem(this);

	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/

	
}
