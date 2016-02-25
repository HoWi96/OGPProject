package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * This class is all about the units of the game.
 * @author Holger
 * @date 20/02/2016
 * 
 * 
 */

/** TO BE ADDED TO CLASS HEADING
 * @invar  The Name of each Unit must be a valid Name for any
 *         Unit.
 *       | isValidName(getName())
 */

public class Unit { 
	
/**
 * Initialize this new Unit with given Name.
 *
 * @param  name
 *         The Name for this new Unit.
 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 */
public Unit(String name)
		throws IllegalArgumentException {
	this.setName(name);
}


/**
 * Return the Name of this Unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Check whether the given Name is a valid Name for
 * any Unit.
 *  
 * @param  Name
 *         The Name to check.
 * @return 
 *       | result == 
*/
public static boolean isValidName(String name) {
	if(name.length()>2){
		//testen
		if(Character.isUpperCase(name.charAt(0))){
			// testen
			if(name.matches("[A-Za-z\"']+")){
				return true;
			}	
		}
	}
	return false;
}

/**
 * Set the Name of this Unit to the given Name.
 * 
 * @param  name
 *         The new Name for this Unit.
 * @post   The Name of this new Unit is equal to
 *         the given Name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         The given Name is not a valid Name for any
 *         Unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

/**
 * Variable registering the Name of this Unit.
 */
private String name;
}
