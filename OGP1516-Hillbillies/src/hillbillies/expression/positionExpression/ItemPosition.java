package hillbillies.expression.positionExpression;

import be.kuleuven.cs.som.annotate.Model;
import hillbillies.expression.Expression;
import hillbillies.model.Item;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class ItemPosition<T extends Item> extends Expression<CubePosition> {

	private final Class<T> type;
	
	public ItemPosition(Class<T> type) {
		this.type = type;
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return taskHandler.getWorld().getAllItems()
				.stream()
				.filter((Item item)-> item.getClass() == getType())
				.map((Item item)-> getType().cast(item))
				.findAny()
				.get()
				.getCubePosition();
	}

	/**
	 * @return the type
	 */
	@Model
	private Class<T> getType() {
		return type;
	}

}