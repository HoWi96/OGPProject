package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;

public class Not extends UnairyOperation {

	public Not(Expression<Boolean> expression) {
		super(expression);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return !getExpression().evaluate(taskHandler);
	}

}
