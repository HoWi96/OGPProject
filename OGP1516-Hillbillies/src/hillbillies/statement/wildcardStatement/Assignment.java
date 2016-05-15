package hillbillies.statement.wildcardStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statement.Statement;

public class Assignment extends Statement {
	
	private final String variableName;

	public Assignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super(value,sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		taskHandler.assign(getVariableName(), getExpression());

	}
	
	/**
	 * @return the variableName
	 */
	public final String getVariableName() {
		return variableName;
	}

}