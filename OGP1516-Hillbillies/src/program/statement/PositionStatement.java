package program.statement;

import hillbillies.model.Position;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public abstract class PositionStatement extends Statement {

	private final Expression<Position> position;

	public PositionStatement(Expression<Position> position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	/**
	 * @return the position
	 */
	public Expression<Position> getPosition() {
		return position;
	}

}
