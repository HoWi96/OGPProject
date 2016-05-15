package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;

public class Log extends Expression<CubePosition> {

	public Log(SourceLocation sourceLocation) {
		super(sourceLocation);
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
