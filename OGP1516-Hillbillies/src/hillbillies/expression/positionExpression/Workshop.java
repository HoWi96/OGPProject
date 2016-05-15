package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class Workshop extends Expression<CubePosition> {

	public Workshop() {
		super();
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getWorld().getAllWorkshops()
				.stream()
				.findAny()
				.get();
	}
	
	

}
