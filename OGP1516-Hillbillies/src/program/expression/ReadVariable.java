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
		return (T) taskHandler.getValueOfVariable(getVariableName()).evaluate(taskHandler);
	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
