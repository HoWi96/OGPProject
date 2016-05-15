package hillbillies.expression.unitExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class This extends Expression<Unit> {

	public This(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit();
	}

}
