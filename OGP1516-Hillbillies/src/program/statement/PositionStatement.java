package program.statement;

import hillbillies.model.Position;
import hillbillies.part3.programs.SourceLocation;
import program.expression.E;

public abstract class PositionStatement extends S {

	private final E<Position> position;

	public PositionStatement(E<Position> position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	/**
	 * @return the position
	 */
	public E<Position> getPosition() {
		return position;
	}

}
