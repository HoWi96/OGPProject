package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class LogPosition extends Expression<CubePosition> {

	public LogPosition() {
		super();
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getWorld().getAllLogs()
				.stream()
				.findAny()
				.get()
				.getCubePosition();
	}

}
