package hillbillies.expression.positionExpression;

import hillbillies.model.Item;
import hillbillies.model.Log;
import hillbillies.model.TaskHandler;
import hillbillies.model.helper.CubePosition;

@Deprecated
public class LogPosition extends ItemPosition<Log> {

	public LogPosition() {
		super(Log.class);
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
	
		return taskHandler.getWorld().getAllItems()
				.stream()
				.filter((Item item)-> item instanceof Log)
				.map((Item item)-> (Log) item)
				.findAny()
				.get()
				.getCubePosition();
				
	}
	
	

}
