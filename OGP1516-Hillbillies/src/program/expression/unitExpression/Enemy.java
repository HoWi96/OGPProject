package program.expression.unitExpression;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;

public class Enemy extends Expression<Unit> {

	public Enemy(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

}
