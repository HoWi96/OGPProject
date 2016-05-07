package program.expression;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

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
