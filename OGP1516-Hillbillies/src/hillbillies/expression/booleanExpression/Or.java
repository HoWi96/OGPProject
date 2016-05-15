package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

public class Or extends BinairyOperation {

	public Or(Expression<Boolean> left, Expression<Boolean> right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return getLeft().evaluate(taskHandler) || getRight().evaluate(taskHandler);
	}

}
