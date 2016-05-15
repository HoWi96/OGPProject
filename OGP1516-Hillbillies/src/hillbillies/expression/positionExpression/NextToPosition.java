package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;


public class NextToPosition extends Expression<CubePosition> {

	private final Expression<CubePosition> position;

	public NextToPosition(Expression<CubePosition> position) {
		super();
		this.position = position;
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		int[] cube = taskHandler.getUnit().getCubePosition().toArray();
		int[] nextTo = taskHandler.getWorld().findReachableAdjacents(cube)
				.stream()
				.findAny()
				.get();
		return new CubePosition(nextTo);
	}

	/**
	 * @return the position
	 */
	public Expression<CubePosition> getPosition() {
		return position;
	}

}
