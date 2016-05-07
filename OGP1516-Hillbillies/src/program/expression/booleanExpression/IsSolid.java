package program.expression.booleanExpression;

import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class IsSolid extends Expression<Boolean> {

	private final Expression<CubePosition> position;

	public IsSolid(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
		this.position = position;
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the position
	 */
	public Expression<CubePosition> getPosition() {
		return position;
	}

}
