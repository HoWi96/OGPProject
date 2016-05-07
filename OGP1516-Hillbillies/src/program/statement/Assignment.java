package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.TaskHandler;

public class Assignment extends Statement {
	
	private final String variableName;

	public Assignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		super();
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
