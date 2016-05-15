package hillbillies.expression.unitExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class Enemy extends Expression<Unit> {

	public Enemy(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		return taskHandler.getWorld().getAllUnits()
				.stream()
				.filter((Unit u)-> u.getFaction() != unit.getFaction())
				.findAny()
				.get();
		
	}

}
