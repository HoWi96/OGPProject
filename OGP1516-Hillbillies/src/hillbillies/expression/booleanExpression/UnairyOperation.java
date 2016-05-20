package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;

public abstract class UnairyOperation extends Expression<Boolean> {

	private final Expression<Boolean> expression;

	public UnairyOperation(Expression<Boolean> expression) {
		this.expression = expression;
	}

	/**
	 * @return the expression
	 */
	public Expression<Boolean> getExpression() {
		return expression;
	}

}
