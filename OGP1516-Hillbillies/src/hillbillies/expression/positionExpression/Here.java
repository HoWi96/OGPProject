package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.helper.CubePosition;

public class Here extends Expression<CubePosition> {

	public Here() {
		super();
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getUnit().getCubePosition();
	}

}
