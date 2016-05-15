package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;

public class isFriend extends UnitInspector {

	public isFriend(Expression<Unit> unit) {
		super(unit);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit().getFaction() == 
				getUnit().evaluate(taskHandler).getFaction();
	}

}
