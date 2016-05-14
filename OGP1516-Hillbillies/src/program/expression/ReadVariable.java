package program.expression;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

public class ReadVariable extends Expression<Object> {

	private final String variableName;

	public ReadVariable(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public Object evaluate(TaskHandler taskHandler) {
		return taskHandler.getValueOfVariable(getVariableName()).evaluate(taskHandler);
	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
