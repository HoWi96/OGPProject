package program.statement;

import hillbillies.model.Position;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.TaskHandler;

public class MoveTo extends PositionStatement {

	public MoveTo(Expression<Position> position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

}
