package program.statement.wildcardStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class Assignment extends Statement {
	
	private final String variableName;

	public Assignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super(value,sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		taskHandler.assign(getVariableName(), getExpression());
		setExecuted(true);

	}
	
	/**
	 * @return the variableName
	 */
	public final String getVariableName() {
		return variableName;
	}

}