package hillbillies.expression;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

public abstract class Expression<T> {
	
	private SourceLocation sourceLocation;

	public Expression(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	/**
	 * Method to retrieve the sourceLocation
	 * 
	 * @return SourceLocation sourceLocation
	 */
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	/**
	 * Method to evaluate an expression
	 * 
	 * @param taskHandler
	 * 		the class who handles the task
	 * @return T t
	 */
	public abstract T evaluate(TaskHandler taskHandler);

}
