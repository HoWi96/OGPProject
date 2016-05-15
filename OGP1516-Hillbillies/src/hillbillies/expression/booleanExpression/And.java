package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;

public class And extends BinairyOperation {

	public And(Expression<Boolean> left, Expression<Boolean> right) {
		super(left, right);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return getLeft().evaluate(taskHandler) && getRight().evaluate(taskHandler);
	}

}
