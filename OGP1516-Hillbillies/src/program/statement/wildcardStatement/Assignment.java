package program.statement.wildcardStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class Assignment<T> extends Statement<T> {
	
	private final String variableName;

	public Assignment(String variableName, Expression<T> value, SourceLocation sourceLocation) {
		super(value,sourceLocation);
		this.variableName = variableName;

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

}
