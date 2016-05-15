package hillbillies.expression.unitExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;

public class This extends Expression<Unit> {

	public This() {
		super();
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit();
	}

}
