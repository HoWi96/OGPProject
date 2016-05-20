package hillbillies.expression.positionExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.helper.CubePosition;
import hillbillies.model.Boulder;
import hillbillies.model.Item;

@Deprecated
public class BoulderPosition extends ItemPosition<Boulder> {

	public BoulderPosition() {
		super(Boulder.class);
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
	
		return taskHandler.getWorld().getAllItems()
				.stream()
				.filter((Item item)-> item instanceof Boulder)
				.map((Item item)-> (Boulder) item)
				.findAny()
				.get()
				.getCubePosition();
				
	}

}
