package hillbillies.model;

import java.util.HashMap;

import be.kuleuven.cs.som.annotate.*;
import program.expression.Expression;
import program.statement.Statement;

/**
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

private HashMap<String,Expression<?>> assignedVariablesMap = new  HashMap<String,Expression<?>>();

public void assign(String variableName, Expression<?> expression) {
	assignedVariablesMap.put(variableName, expression);
}

public Expression<?> getValueOfVariable(String variableName) {
	return assignedVariablesMap.get(variableName);
}

//EXECUTE TASK

public void executeTask(double dt){
	
	while(dt>0.001){
		dt-=0.001;
		if(!getUnit().isExecutingStatement()){
			getCurrentStatement().execute(this);
			
			Statement nextStatement = getCurrentStatement().getNext();
			if(nextStatement != null){
				setCurrentStatement(nextStatement);
			} else {
				
				Statement previousStatement = getCurrentStatement().getPrevious();
				if(previousStatement != null){
					setCurrentStatement(previousStatement);
				} else {
					getTask().terminate();
				}
			}
		}	
	}	
}

public void interruptTask(){
		Task task = this.getTask();
		task.removeUnit();
		task.setPriority(task.getPriority() - 100);
		task.getActivity().setNext(null);
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

