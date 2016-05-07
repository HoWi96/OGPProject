package program.statement.positionStatement;


import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public abstract class PositionStatement extends Statement {

	private final Expression<CubePosition> position;

	public PositionStatement(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	/**
	 * @return the position
	 */
	public Expression<CubePosition> getPosition() {
		return position;
	}

}
