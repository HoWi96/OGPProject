package program.statement;


import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public abstract class Statement{	
	
private final SourceLocation sourceLocation;	
private final Expression<?> expression;
private boolean isExecuted;

/**
 * Initialize this new S with given sourceLocation.
 * 
 * @param  sourceLocation
 *         The sourceLocation for this new S.
 * @post   The sourceLocation of this new S is equal to the given
 *         sourceLocation.
 *       | new.getSourceLocation() == sourceLocation
 */ 
public Statement(Expression<?> expression, SourceLocation sourceLocation) {
	this.expression = expression;
	this.sourceLocation = sourceLocation;
	this.setExecuted(false);
}

/**
 * Return the sourceLocation of this S.
 */
@Basic @Raw @Immutable
public SourceLocation getSourceLocation() {
	return this.sourceLocation;
}

/**
 * A method to execute the statement
 * 
 * @param taskHandler
 * 		the handler of the task
 */
public abstract void execute(TaskHandler taskHandler);

/**
 * Returns whether the activity is executed
 */
@Basic @Raw
public boolean isExecuted(){
	return this.isExecuted;
}

/**
 * @param executed the isExecuted to set
 */
public void setExecuted(boolean executed) {
	this.isExecuted = executed;
}

/**
 * @param taskHandler
 * @return the next Statement
 */
public Statement getNext(TaskHandler taskHandler) {
	return null;
}

/**
 * @return the expression
 */
public Expression<?> getExpression() {
	return expression;
}







}
