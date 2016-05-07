package program.expression.booleanExpression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

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
