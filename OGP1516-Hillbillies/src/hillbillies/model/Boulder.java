package hillbillies.model;

/**
 * @author Holger
 * @version 1.0
 */

public class Boulder extends RawMaterial {
	
	
	/**
	 * Creates a boulder on a given position in a given world
	 * 
	 * @param position
	 * 		the new position
	 * @param world
	 * 		the new world
	 * @effect
	 * 		tthe boulder will be created as a raw material with the given world and given position
	 */
	public Boulder(int[] position, World world) throws IllegalArgumentException{
		super(position, world);
	}


}
