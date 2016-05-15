package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;

public class IsFriend extends UnitInspector {

	public IsFriend(Expression<Unit> unit) {
		super(unit);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit().getFaction() == 
				getUnit().evaluate(taskHandler).getFaction();
	}

}
