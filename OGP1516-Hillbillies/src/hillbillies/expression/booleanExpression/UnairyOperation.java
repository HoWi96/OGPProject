package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnairyOperation extends Expression<Boolean> {

	private final Expression<Boolean> expression;

	public UnairyOperation(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}

	/**
	 * @return the expression
	 */
	public Expression<Boolean> getExpression() {
		return expression;
	}

}
