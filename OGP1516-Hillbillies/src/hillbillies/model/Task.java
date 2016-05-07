package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Invar  The Unit of each Task must be a valid Unit for any
 *         Task.
 *       | isValidUnit(getUnit())
 */

public class Task {
	
/**
 * Initialize this new Task with given Unit.
 *
 * @param  unit
 *         The Unit for this new Task.
 * @effect The Unit of this new Task is set to
 *         the given Unit.
 *       | this.setUnit(unit)
 */
public Task(Unit unit)
		throws IllegalArgumentException {
	this.setUnit(unit);
}


/**
 * Return the Unit of this Task.
 */
@Basic @Raw
public Unit getUnit() {
	return this.unit;
}

/**
 * Check whether the given Unit is a valid Unit for
 * any Task.
 *  
 * @param  Unit
 *         The Unit to check.
 * @return 
 *       | result == true;
*/
public static boolean isValidUnit(Unit unit) {
	return true;
}

/**
 * Set the Unit of this Task to the given Unit.
 * 
 * @param  unit
 *         The new Unit for this Task.
 * @post   The Unit of this new Task is equal to
 *         the given Unit.
 *       | new.getUnit() == unit
 * @throws IllegalArgumentException
 *         The given Unit is not a valid Unit for any
 *         Task.
 *       | ! isValidUnit(getUnit())
 */
@Raw
public void setUnit(Unit unit) 
		throws IllegalArgumentException {
	if (! isValidUnit(unit))
		throw new IllegalArgumentException();
	this.unit = unit;
}

/**
 * Variable registering the Unit of this Task.
 */
private Unit unit;

}
