package program.statement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Print extends Statement {

	private final Expression<?> expression;

	public Print(Expression<?> expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		System.out.println(getExpression().evaluate(taskHandler).toString());
	}

	/**
	 * @return the value
	 */
	public Expression<?> getExpression() {
		return expression;
	}

}
