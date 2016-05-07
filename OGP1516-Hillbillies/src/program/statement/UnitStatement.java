package program.statement;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.E;

public abstract class UnitStatement extends S {

	private final E<Unit> unit;

	public UnitStatement(E<Unit> unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unit = unit;
	}
	
	/**
	 * @return the unit
	 */
	public E<Unit> getUnit() {
		return unit;
	}

}
