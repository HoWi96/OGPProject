package program.expression.unitExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Enemy extends Expression<Unit> {

	public Enemy(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		return taskHandler.getClosestEnemy();
	}

}
