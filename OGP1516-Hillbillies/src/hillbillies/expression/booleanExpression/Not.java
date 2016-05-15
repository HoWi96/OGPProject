package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

public class Not extends UnairyOperation {

	public Not(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return !getExpression().evaluate(taskHandler);
	}

}
