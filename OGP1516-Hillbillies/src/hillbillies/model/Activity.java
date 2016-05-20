
package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class about the activities of the game
 * 
 * @author Holger Willems | 2e bach. ing. OOP
 * @date 16/05/2016
 * @Version 3.0
 * 
 */

/*
 * This is an enum containing all the activities that can carried out by a unit
 */
@Value
public enum Activity {
	
	NOTHING,
	MOVING,
	WORKING,
	ATTACKING,
	RESTING,
	FALLING;
}