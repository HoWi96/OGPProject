package hillbillies.expression;

import hillbillies.model.TaskHandler;

public abstract class Expression<T> {

	public Expression() {}

	/**
	 * Method to evaluate an expression
	 * 
	 * @param taskHandler
	 * 		the class who handles the task
	 * @return T t
	 */
	public abstract T evaluate(TaskHandler taskHandler);

}
