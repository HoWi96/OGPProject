package program.expression.booleanExpression;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Parentheses extends UnairyOperation {

	public Parentheses(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

}
