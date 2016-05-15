package hillbillies.statement.wildcardStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.statement.Statement;

public class Assignment extends Statement {
	
	private final String variableName;

	public Assignment(String variableName, Expression<?> value) throws IllegalArgumentException {
		super(value);
		if(variableName == null)
			throw new IllegalArgumentException("variable name can't be null");
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