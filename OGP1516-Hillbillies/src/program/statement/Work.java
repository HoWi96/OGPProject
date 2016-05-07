package program.statement;

import hillbillies.model.Position;
import hillbillies.part3.programs.SourceLocation;
import program.expression.E;
import program.expression.TaskHandler;

public class Work extends PositionStatement {

	public Work(E<Position> position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

}
