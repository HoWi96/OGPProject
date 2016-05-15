package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class LogExpression extends Expression<CubePosition> {

	public LogExpression() {
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
