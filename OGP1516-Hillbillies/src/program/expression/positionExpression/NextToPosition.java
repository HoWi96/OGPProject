package program.expression.positionExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class NextToPosition extends Expression<CubePosition> {

	private final Expression<CubePosition> position;

	public NextToPosition(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		
		int[] pos = getPosition().evaluate(taskHandler).toArray();
		taskHandler.getWorld().findReachableAdjacents(pos);
		
		
		
	}

	/**
	 * @return the position
	 */
	public Expression<CubePosition> getPosition() {
		return position;
	}

}
