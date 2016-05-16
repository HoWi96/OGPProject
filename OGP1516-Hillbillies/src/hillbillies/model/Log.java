package hillbillies.model;

/**
 * A class about the logs of the game
 * 
 * @author Holger Willems | 2e bach. ing. OOP
 * @date 16/05/2016
 * @Version 3.0
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