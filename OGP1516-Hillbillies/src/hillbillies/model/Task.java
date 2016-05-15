package hillbillies.model;

import java.util.HashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.statement.Statement;

/** 
 * ATTRIBUTES
 * 
 * @Invar  The priority of each Task must be a valid priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 * @Invar  Each Task can have its name as name.
 *       | canHaveAsName(this.getName())
 * @Invar  Each Task can have its Activity as Activity.
 *       | canHaveAsActivity(this.getActivity())
 *       
 * ASSOCIATIONS
 * @Invar  The Unit of each Task must be a valid Unit for any
 *         Task.
 *       | isValidUnit(getUnit())  
 */

public class Task {



/**
 * Initialize this new Task with given priority and
 *  a given name and a given activity and an empty set of schedulers.
 *
 * @param  priority
 *         The priority for this new Task.
 * @param  name
 *         The name for this new Task.
 * @param  activity
 *         The Activity for this new Task.
 *         
 * @effect The priority of this new Task is set to
 *         the given priority.
 *       | this.setPriority(priority)
 * @post   The name of this new Task is equal to the given
 *         name.
 *       | new.getName() == name
 * @post   The Activity of this new Task is equal to the given
 *         Activity.
 *       | new.getActivity() == activity
 * @throws IllegalArgumentException
 *         This new Task cannot have the given name as its name.
 *       | ! canHaveAsName(this.getName())
 * @throws IllegalArgumentException
 *         This new Task cannot have the given Activity as its Activity.
 *       | ! canHaveAsActivity(this.getActivity())       
 */
public Task(String name, int priority,Statement activity)throws IllegalArgumentException {
	
	if (! canHaveAsName(name))
		throw new IllegalArgumentException("Invalid name for a task");
	if (! canHaveAsActivity(activity))
		throw new IllegalArgumentException("invalid activity for a task");
	
	this.setPriority(priority);
	this.name = name;
	this.activity = activity;
	this.schedulers = new HashSet<>();
	this.isTerminated = false;
}

public void terminate(){
	
	this.removeUnit();
	for(Scheduler scheduler: getAllSchedulers()){
		scheduler.removeAsTask(this);
	}
	
	this.isTerminated = true;
	
}

private boolean isTerminated;

public boolean isTerminated(){
	return this.isTerminated;
}

//PRIORITY

/**
 * Return the priority of this Task.
 */
@Basic @Raw
public int getPriority() {
	return this.priority;
}

/**
 * Check whether the given priority is a valid priority for
 * any Task.
 *  
 * @param  priority
 *         The priority to check.
 * @return 
 *       | result == priority > Integer.MIN_VALUE+200
*/
public static boolean isValidPriority(int priority) {
	return priority >= Integer.MIN_VALUE+200;
}

/**
 * Set the priority of this Task to the given priority.
 * 
 * @param  priority
 *         The new priority for this Task.
 * @post   The priority of this new Task is equal to
 *         the given priority or if invalid the min value of integer.      
 */
@Raw
public void setPriority(int priority) {
	if (! isValidPriority(priority))
		this.priority = Integer.MIN_VALUE+200;
	else
		this.priority = priority;
}

/**
 * Variable registering the priority of this Task.
 */
private int priority;
	
//NAME

/**
 * Return the name of this Task.
 */
@Basic @Raw @Immutable
public String getName() {
	return this.name;
}

/**
 * Check whether this Task can have the given name as its name.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == name != null;
*/
@Raw
public boolean canHaveAsName(String name) {
	return name != null;
}

/**
 * Variable registering the name of this Task.
 */
private final String name;

// ACTIVITIES

/**
 * Return the Activity of this Task.
 */
@Basic @Raw @Immutable
public Statement getActivity() {
	return this.activity;
}

/**
 * Check whether this Task can have the given Activity as its Activity.
 *  
 * @param  activity
 *         The Activity to check.
 * @return 
 *       | result == activity != null
*/
@Raw
public boolean canHaveAsActivity(Statement activity) {
	return activity != null;
}

/**
 * Variable registering the Activity of this Task.
 */
private final Statement activity;

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------UNITS--------------------------------------
 * ------------------CONTROLLING CLASS-------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/


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
 *       | result == (unit == null || !unit.hasTask() || unit.getTask() == this)
*/
public boolean canHaveAsUnit(Unit unit) {
	return (unit == null || !unit.hasTask() || unit.getTask() == this);
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
@Model @Raw
private void setUnit(Unit unit) 
		throws IllegalArgumentException {
	if (! canHaveAsUnit(unit))
		throw new IllegalArgumentException();
	this.unit = unit;
}
	
/**
 * Variable registering the Unit of this Task.
 */
private Unit unit;

//ASSOCIATION

/**
 * Set up a bidirectional association between the task and unit.
 * 
 * @param unit
 * @throws IllegalArgumentException
 */
public void addUnit(Unit unit) throws IllegalArgumentException{
	if (unit == null || unit.hasTask())
		throw new IllegalArgumentException("invalid unit for task");
	
	this.setUnit(unit);
	unit.setTask(this);
}
/**
 * Tears down the bidirectional association between the task and unit.
 * 
 * @throws IllegalStateException
 */
public void removeUnit() throws IllegalStateException{
	if(!hasUnit())
		throw new IllegalStateException("no unit assigned to this task");
	
	this.getUnit().setTask(null);
	this.setUnit(null);
}
/**
 * Checks if a unit is assigned to this task
 * 
 * @return 
 * 		|this.getUnit() != null
 */
public boolean hasUnit(){
	return this.getUnit() != null;
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------SCHEDULERS---------------------------------
 * ------------------NON CONTROLLING CLASS---------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/

/**
 * Variable referencing a set collecting all the schedulers
 * of this task.
 *
 * @Invar The referenced set is effective.
 * 		| schedulers != null
 * @Invar Each scheduler registered in the referenced list is
 * effective and not yet terminated.
 * 		| for each scheduler in schedulers:
 * 		| ( (scheduler != null) &&
 * 		| (! scheduler.isTerminated()) )
 */
private final Set<Scheduler> schedulers;

//BASIC INSPECTOR

public boolean hasAsScheduler(Scheduler scheduler){
	return schedulers.contains(scheduler);
}

public Set<Scheduler> getAllSchedulers(){
	return new HashSet<Scheduler>(schedulers);
}

//CHECKERS

public boolean canHaveAsScheduler(Scheduler scheduler){
	return scheduler != null;
}

public boolean hasProperSchedulers(){
	for(Scheduler scheduler: schedulers){
		if(!canHaveAsScheduler(scheduler))
			return false;
		if(!scheduler.hasAsTask(this))
			return false;
	}
	return true;
}

//ASSOCIATION

public void addAsScheduler(Scheduler scheduler) throws IllegalArgumentException{
	if(!canHaveAsScheduler(scheduler))
		throw new IllegalArgumentException("invalid scheduler for task");
	schedulers.add(scheduler);
}

public void removeAsScheduler(Scheduler scheduler) throws IllegalArgumentException{
	if(!hasAsScheduler(scheduler))
		throw new IllegalArgumentException("scheduler is not part of task");
	schedulers.remove(scheduler);
}

//STATIC TASKS CONSTRUCTOR

















}
