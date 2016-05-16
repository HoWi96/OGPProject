package hillbillies.statement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;

public abstract class Statement implements Istatement{	
	
private final Expression<?> expression;
private Statement next;
private Statement previous;


/**
 * Initialize this new S with given sourceLocation.
 * 
 * @post   The sourceLocation of this new S is equal to the given
 *         sourceLocation.
 *       | new.getSourceLocation() == sourceLocation
 */ 
public Statement(Expression<?> expression){
	this.expression = expression;
	this.setNext(null);
	this.setPrevious(null);	
}

//ATTRIBUTES

/**
 * @return the expression
 */
public Expression<?> getExpression() {
	return expression;
}


//WORKFLOW


/**
 * A method to execute the statement
 * 
 * @param taskHandler
 * 		the handler of the task
 */
@Override
public abstract void execute(TaskHandler taskHandler);

/**
 * @return the previous
 */
@Override
public Statement getPrevious() {
	return previous;
}

/**
 * @param previous the previous to set
 */
public void setPrevious(Statement previous) {
	this.previous = previous;
}

/**
 * @return the next
 */
@Override
public Statement getNext() {
	return next;
}

/**
 * @param next the next to set
 */
public void setNext(Statement next) {
	this.next = next;
}







}
