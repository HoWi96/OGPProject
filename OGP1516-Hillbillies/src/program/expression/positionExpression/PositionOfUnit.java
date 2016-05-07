package program.expression.positionExpression;

import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class PositionOfUnit extends Expression<CubePosition> {

	private final Expression<Unit> unit;

	public PositionOfUnit(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unit = unit;
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return getUnit().evaluate(taskHandler).getCubePosition();
	}

	/**
	 * @return the unit
	 */
	public Expression<Unit> getUnit() {
		return unit;
	}

}
