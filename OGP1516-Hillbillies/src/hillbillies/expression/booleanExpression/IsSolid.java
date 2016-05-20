package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.helper.CubePosition;

public class IsSolid extends Expression<Boolean> {

	private final Expression<CubePosition> position;

	public IsSolid(Expression<CubePosition> position) {
		super();
		this.position = position;
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return taskHandler.getWorld().isSolidCube(getPosition().evaluate(taskHandler).toArray());
	}

	/**
	 * @return the position
	 */
	public Expression<CubePosition> getPosition() {
		return position;
	}

}
