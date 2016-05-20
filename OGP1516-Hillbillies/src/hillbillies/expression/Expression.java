package hillbillies.expression;

import be.kuleuven.cs.som.annotate.Model;
import hillbillies.model.TaskHandler;

public abstract class Expression<T> implements IExpression<T> {
	
	@Model
	protected Expression() {};

	/**
	 * Method to evaluate an expression
	 * 
	 * @param taskHandler
	 * 		the class who handles the task
	 * @return T t
	 */
	@Override
	public abstract T evaluate(TaskHandler taskHandler);

}
