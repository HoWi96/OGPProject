package program.expression.booleanExpression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class And extends BinairyOperation {

	public And(Expression<Boolean> left, Expression<Boolean> right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return getLeft().evaluate(taskHandler) && getRight().evaluate(taskHandler);
	}

}
