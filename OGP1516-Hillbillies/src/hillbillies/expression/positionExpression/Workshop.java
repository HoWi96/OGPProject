package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;

public class Workshop extends Expression<CubePosition> {

	public Workshop(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getWorld().getAllWorkshops()
				.stream()
				.findAny()
				.get();
	}
	
	

}
