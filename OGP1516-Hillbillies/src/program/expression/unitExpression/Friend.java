package program.expression.unitExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Friend extends Expression<Unit> {

	public Friend(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		return unit.getFaction().getAllUnits()
				.stream()
				.filter((Unit u) -> u != unit)
				.findAny()
				.get();
	}

}
