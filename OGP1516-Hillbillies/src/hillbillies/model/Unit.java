package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;


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
 * Create a new unit with the given attributes.
	 * 
	 * @param name
	 *            The name of the unit.
	 * @param initialPosition
	 *            The initial position of the unit, as an array with 3 elements
	 *            {x, y, z}.
	 * @param weight
	 *            The initial weight of the unit
	 * @param agility
	 *            The initial agility of the unit
	 * @param strength
	 *            The initial strength of the unit
	 * @param toughness
	 *            The initial toughness of the unit
	 * @param enableDefaultBehavior
	 *            Whether the default behavior of the unit is enabled
	 * 
	 * @return The generated unit
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown

 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 * Initialize this new Unit with given weight.
 * 
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight. Otherwise, the weight of this new Unit is equal
 *         to 100.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == 100
 *       
 */
public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
		boolean enableDefaultBehavior)
		throws IllegalArgumentException {
	this.setName(name);
	if (!isValidWeight(weight,strength,agility))
		weight = 100;
	setWeight(weight);
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
 *       | result == (name.length()>2)&&(Character.isUpperCase(name.charAt(0))&&(name.matches("[A-Za-z\"' ]+")))
*/
public static boolean isValidName(String name) {
	return (name.length()>2)&&(Character.isUpperCase(name.charAt(0))&&(name.matches("[A-Za-z\"' ]+")));
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

/**
 * @invar  The weight of each Unit must be a valid weight for any
 *         Unit.
 *       | isValidWeight(getWeight())
 */


/**
 * Return the weight of this Unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Check whether the given weight is a valid weight for
 * any Unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == (1<=weight&&weight<=200)&&(weight>(strength+agility)/2)
*/
public static boolean isValidWeight(int weight,int strength, int agility) {
	return (1<=weight&&weight<=200)&&(weight>(strength+agility)/2) ;
}

/**
 * Set the weight of this Unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this Unit.
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight,this.getStrenght(),this.getAgility))
		this.weight = weight;
}

/**
 * Variable registering the weight of this Unit.
 */
private int weight;

/**
 * @invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidStrength(getStrength())
 */

/**
 * Initialize this new Unit with given Strength.
 * 
 * @param  strength
 *         The Strength for this new Unit.
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength. Otherwise, the Strength of this new Unit is equal
 *         to 100.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 100
 */
public Unit(int strength) {
	if (! isValidStrength(strength))
		strength = 100;
	setStrength(strength);
}

/**
 * Return the Strength of this Unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Check whether the given Strength is a valid Strength for
 * any Unit.
 *  
 * @param  strength
 *         The Strength to check.
 * @return 
 *       | result == (1<=weight&&weight<=200)
*/
public static boolean isValidStrength(int strength) {
	return (1<=strength&&strength<=200);
	
}

/**
 * Set the Strength of this Unit to the given Strength.
 * 
 * @param  strength
 *         The new Strength for this Unit.
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
public void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}

/**
 * Variable registering the Strength of this Unit.
 */
private int strength;



}
