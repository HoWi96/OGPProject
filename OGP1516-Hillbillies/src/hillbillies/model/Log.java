package hillbillies.model;

/**
 * @author Holger Willems |2e bach. ing.: OOP
 * @date 10/04/2016
 * @Version 2.0
 * 
 */

public class Log extends RawMaterial{
	
	/**
	 * Creates a log on a given position in a given world
	 * 
	 * @param position
	 * 		the new position
	 * @param world
	 * 		the new world
	 * @effect
	 * 		the log will be created as a raw material with the given world and given position
	 */
	public Log(int[] position, World world) throws IllegalArgumentException{
		super(position, world);
	}
}
