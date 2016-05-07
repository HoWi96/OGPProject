package program.statement.positionStatement;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class MoveTo extends Statement<CubePosition> {

	public MoveTo(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		int[] cube = getExpression().evaluate(taskHandler).toArray();
		unit.moveTo(cube);
	}

}
