package program.statement;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public abstract class UnitStatement extends Statement {

	private final Expression<Unit> unit;

	public UnitStatement(Expression<Unit> unit, SourceLocation sourceLocation) {
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
