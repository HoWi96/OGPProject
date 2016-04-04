package hillbillies.model;

/**
 * @author Holger
 * @version 1.0
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
	 */
	public RawMaterial(int[] position, World world) throws IllegalArgumentException {
		super();
		world.addItem(this);
		this.setPosition(Utils.getCubeCenter(position));
		

	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/

	
}
