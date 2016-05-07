package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.position.CubePosition;
import program.expression.Expression;

/**
 * @invar  Each TaskHandler can have its Task as Task.
 *       | canHaveAsTask(this.getTask())
 */

public class TaskHandler {
	
private Unit unit;
private World world;

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
public TaskHandler(Task task) throws IllegalArgumentException {
	if (! canHaveAsTask(task))
		throw new IllegalArgumentException();
	this.task = task;
}

/**
 * Return the Task of this TaskHandler.
 */
@Basic @Raw @Immutable
public Task getTask() {
	return this.task;
}

/**
 * Check whether this TaskHandler can have the given Task as its Task.
 *  
 * @param  task
 *         The Task to check.
 * @return 
 *       | result == 
*/
@Raw
public boolean canHaveAsTask(Task task) {
	return false;
}

/**
 * @return the unit
 */
public Unit getUnit() {
	return unit;
}

/**
 * @param unit the unit to set
 */
public void setUnit(Unit unit) {
	this.unit = unit;
}

/**
 * @return the world
 */
public World getWorld() {
	return world ;
}

/**
 * @param world the world to set
 */
public void setWorld(World world) {
	this.world = world;
}

/**
 * Variable registering the Task of this TaskHandler.
 */
private final Task task;

public CubePosition getClosestBoulder() {
	// TODO Auto-generated method stub
	return null;
}

public CubePosition getClosestLog() {
	// TODO Auto-generated method stub
	return null;
}

public void stop() {
	// TODO Auto-generated method stub
	
}

public CubePosition getClosestWorkshop() {
	// TODO Auto-generated method stub
	return null;
}

public CubePosition getNextToPosition(CubePosition evaluate) {
	// TODO Auto-generated method stub
	return null;
}

public Unit getClosestUnit() {
	// TODO Auto-generated method stub
	return null;
}

public Unit getClosestEnemy() {
	// TODO Auto-generated method stub
	return null;
}

public Unit getClosestFriend() {
	// TODO Auto-generated method stub
	return null;
}

public Expression<?> getValueOfVariable(String variableName) {
	// TODO Auto-generated method stub
	return null;
}

public void assign(String variableName, Expression<?> expression) {
	// TODO Auto-generated method stub
	
}
	
}

