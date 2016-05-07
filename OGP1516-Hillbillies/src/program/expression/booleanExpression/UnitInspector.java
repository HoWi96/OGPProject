package program.expression.booleanExpression;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

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
