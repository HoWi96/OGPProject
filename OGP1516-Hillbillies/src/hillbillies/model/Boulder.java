package hillbillies.model;

/**
 * @author Holger Willems |2e bach. ing.: OOP
 * @date 10/04/2016
 * @Version 2.0
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
