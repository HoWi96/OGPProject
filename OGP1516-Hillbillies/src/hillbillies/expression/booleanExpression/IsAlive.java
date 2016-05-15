package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class IsAlive extends UnitInspector {


	public IsAlive(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return getUnit().evaluate(taskHandler).isAlive();
	}

}
