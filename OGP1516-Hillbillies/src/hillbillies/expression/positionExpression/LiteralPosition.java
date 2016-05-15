package hillbillies.expression.positionExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.position.CubePosition;

public class LiteralPosition extends Expression<CubePosition> {

	private final int x, y, z;


	public LiteralPosition(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		
	}

	@Override
	public CubePosition evaluate(TaskHandler taskHandler) {
		return new CubePosition(x,y,z);
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
