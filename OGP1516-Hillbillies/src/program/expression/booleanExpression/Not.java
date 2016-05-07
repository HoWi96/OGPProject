package program.expression.booleanExpression;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Not extends UnairyOperation {

	public Not(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return !getExpression().evaluate(taskHandler);
	}

}
