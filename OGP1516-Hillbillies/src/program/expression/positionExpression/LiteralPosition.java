package program.expression.positionExpression;

import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;

public class LiteralPosition extends Expression<CubePosition> {

	private final int x, y, z;


	public LiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.x = x;
		this.y = y;
		this.z = z;
		
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public final int getZ() {
		return z;
	}

}
