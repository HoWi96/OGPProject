package hillbillies.expression.unitExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;

public class Friend extends Any {

	public Friend() {
		super();
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		return taskHandler.getWorld().getAllUnits()
				.stream()
				.filter((Unit u)-> u.getFaction() == unit.getFaction())
				.filter((Unit u)-> u != unit)
				.findAny()
				.get();
	}

}
