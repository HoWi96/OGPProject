package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.Unit;

public abstract class UnitInspector extends Expression<Boolean> {

	private final Expression<Unit> unit;

	protected UnitInspector(Expression<Unit> unit) {
		super();
		this.unit = unit;
	}

	/**
	 * @return the unit
	 */
	public Expression<Unit> getUnit() {
		return unit;
	}

}
