package hillbillies.model;

/**
 * A class about the boulders of the game
 * 
 * @author Holger Willems | 2e bach. ing. OOP
 * @date 16/05/2016
 * @Version 3.0
 * 
 */

public class Boulder extends Item {
	
	/**
	 * Creates a boulder on a given position in a given world
	 */
	public Boulder(int[] position, World world) throws IllegalArgumentException{
		super(position, world);
	}


}
