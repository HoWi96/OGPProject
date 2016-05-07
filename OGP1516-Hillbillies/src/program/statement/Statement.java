package program.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.TaskHandler;

public abstract class Statement{	
	

/**
 * Initialize this new S with given sourceLocation.
 * 
 * @param  sourceLocation
 *         The sourceLocation for this new S.
 * @post   The sourceLocation of this new S is equal to the given
 *         sourceLocation.
 *       | new.getSourceLocation() == sourceLocation
 */ 
public Statement(SourceLocation sourceLocation) {
	this.sourceLocation = sourceLocation;
}

/**
 * Return the sourceLocation of this S.
 */
@Basic @Raw @Immutable
public SourceLocation getSourceLocation() {
	return this.sourceLocation;
}

/**
 * Variable registering the sourceLocation of this S.
 */
private final SourceLocation sourceLocation;

/**
 * A method to execute the statement
 * 
 * @param taskHandler
 * 		the handler of the task
 */
public abstract void execute(TaskHandler taskHandler);
}
