package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class BoulderPosition extends Expression<CubePosition> {

	public BoulderPosition() {
		super();
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
	
		return taskHandler.getWorld().getAllBoulders()
				.stream()
				.findAny()
				.get()
				.getCubePosition();
				
	}

}
