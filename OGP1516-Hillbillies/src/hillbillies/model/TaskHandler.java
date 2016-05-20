package hillbillies.model;

import java.util.HashMap;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.expression.Expression;
import hillbillies.statement.Statement;

/**
 * A class to handle a task that belongs to a unit
 * 
 * 
 * @Invar  Each TaskHandler can have its Task as Task.
 *       | canHaveAsTask(this.getTask())
 */

public class TaskHandler {

private final World world;
private final Unit unit;
private final Task task;
private Statement currentStatement;

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
public TaskHandler(Unit unit, World world, Task task) throws IllegalArgumentException {
	this.unit = unit;
	this.world = world;
	this.task = task;
	setCurrentStatement(task.getActivity());
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

private final HashMap<String,Expression<?>> assignedVariablesMap = new  HashMap<String,Expression<?>>();

/**
 * 
 * @param variableName
 * @param expression
 * @post 
 * 		|assignedVariablesMap.put(variableName, expression);
 */
public void assign(String variableName, Expression<?> expression) {
	assignedVariablesMap.put(variableName, expression);
}

/**
 * 
 * @param variableName
 * @return Expression<?>
 * 		|assignedVariablesMap.get(variableName);
 */
public Expression<?> getValueOfVariable(String variableName) {
	return assignedVariablesMap.get(variableName);
}

//EXECUTE TASK

/**
 * Execute one statement of a task
 */
public void executeTask(){
	
	if(getCurrentStatement()==null){
		getTask().terminate();
		
	}	else {
	
		//System.out.println("execute statement"+getCurrentStatement().getClass());
		getCurrentStatement().execute(this);
		
		if(getUnit().hasTask()){
			if(getCurrentStatement().getNext() != null)
				setCurrentStatement(getCurrentStatement().getNext());
			
			else if(getCurrentStatement().getPrevious() != null)
					setCurrentStatement(getCurrentStatement().getPrevious());
			
			else 
				setCurrentStatement(null);
			}
}
	}



/**
 * Interrupt a task if it can not be completed
 */
public void interruptTask(){
		Task task = this.getTask();
		task.removeUnit(getUnit());
		task.setPriority(task.getPriority() - 100);
		task.getActivity().setNext(null);
		getUnit().setLeader(null);
		getUnit().setTaskHandler(null);
}

/**
 * @return the currentStatement
 */
public Statement getCurrentStatement() {
	return currentStatement;
}

/**
 * @param currentStatement the currentStatement to set
 */
public void setCurrentStatement(Statement currentStatement) {
	this.currentStatement = currentStatement;
}

/**
 * @return the task
 */
public Task getTask() {
	return task;
}




}

