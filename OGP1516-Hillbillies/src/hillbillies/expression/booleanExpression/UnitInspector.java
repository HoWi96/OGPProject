package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitInspector extends Expression<Boolean> {

	private final Expression<Unit> unit;

	public UnitInspector(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unit = unit;
	}

	/**
	 * @return the unit
	 */
	public Expression<Unit> getUnit() {
		return unit;
	}

}
