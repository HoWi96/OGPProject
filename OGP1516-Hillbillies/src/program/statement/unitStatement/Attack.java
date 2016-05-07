package program.statement.unitStatement;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class Attack extends Statement<Unit> {

	public Attack(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit attacker = taskHandler.getUnit();
		Unit defender = getExpression().evaluate(taskHandler);
		attacker.attack(defender);
	}

}
