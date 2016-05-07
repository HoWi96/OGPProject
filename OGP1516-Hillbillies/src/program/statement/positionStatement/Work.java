package program.statement.positionStatement;


import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Work extends PositionStatement {

	public Work(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

}
