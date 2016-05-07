package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;

public class Assignment extends Statement {
	
	private final String variableName;
	private final Expression<?> value;

	public Assignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;

	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the variableName
	 */
	public final String getVariableName() {
		return variableName;
	}

	/**
	 * @return the value
	 */
	public Expression<?> getValue() {
		return value;
	}

}
