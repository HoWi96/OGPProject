package program.expression.positionExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Here extends Expression<CubePosition> {

	public Here(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit().getCubePosition();
	}

}
