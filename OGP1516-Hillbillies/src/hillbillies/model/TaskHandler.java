package hillbillies.model;

import java.util.HashMap;

import be.kuleuven.cs.som.annotate.*;
import program.expression.Expression;

/**
 * @Invar  Each TaskHandler can have its Task as Task.
 *       | canHaveAsTask(this.getTask())
 */

public class TaskHandler {

private final World world;
private final Unit unit;

/**
 * Initialize this new TaskHandler with given Task.
 * 
 * @param  task
 *         The Task for this new TaskHandler.
 * @post   The Task of this new TaskHandler is equal to the given
 *         Task.
 *       | new.getTask() == task
 * @throws IllegalArgumentException
 *         This new TaskHandler cannot have the given Task as its Task.
 *       | ! canHaveAsTask(this.getTask())
 */
public TaskHandler(Unit unit, World world) throws IllegalArgumentException {
	this.unit = unit;
	this.world = world;

}

//UNIT

/**
 * @return the unit
 */
@Basic @Raw @Immutable
public Unit getUnit() {
	return unit;
}

//WORLD

/**
 * @return the world
 */
public World getWorld() {
	return world ;
}

//ASSIGNING VARIABLES

private HashMap<String,Expression<?>> assignedVariablesMap = new  HashMap<String,Expression<?>>();

public void assign(String variableName, Expression<?> expression) {
	assignedVariablesMap.put(variableName, expression);
}

public Expression<?> getValueOfVariable(String variableName) {
	return assignedVariablesMap.get(variableName);
}


	
}

