package program.expression.positionExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Boulder extends Expression<CubePosition> {

	public Boulder(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getClosestBoulder();
	}

}
