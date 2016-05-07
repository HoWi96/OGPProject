package program.expression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.TaskHandler;

public class ReadVariable<T> extends Expression<T> {

	private final String variableName;

	public ReadVariable(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public T evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
