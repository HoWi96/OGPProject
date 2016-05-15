package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class isFriend extends UnitInspector {

	public isFriend(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit().getFaction() == 
				getUnit().evaluate(taskHandler).getFaction();
	}

}
