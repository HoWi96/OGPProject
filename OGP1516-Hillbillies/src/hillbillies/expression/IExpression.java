package hillbillies.expression;

import hillbillies.model.TaskHandler;

public interface IExpression<T> {

	/**
	 * Method to evaluate an expression
	 * 
	 * @param taskHandler
	 * 		the class who handles the task
	 * @return T t
	 */
	T evaluate(TaskHandler taskHandler);

}