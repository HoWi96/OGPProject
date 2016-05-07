package program.statement;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;

public class Follow extends UnitStatement {

	public Follow(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

}
