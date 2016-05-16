package hillbillies.model;

/**
 * @author Holger Willems |2e bach. ing.: OOP
 * @date 10/04/2016
 * @Version 2.0
 * 
 */

public class Log extends Item{
	
	/**
	 * Creates a log on a given position in a given world
	 */
	public Log(int[] position, World world) throws IllegalArgumentException {
		super(position, world);
	}
	
}