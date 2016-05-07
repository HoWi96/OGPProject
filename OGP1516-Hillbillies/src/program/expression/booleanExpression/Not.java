package program.expression.booleanExpression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Not extends UnairyOperation {

	public Not(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

}
